document.addEventListener('DOMContentLoaded', populatePage, false);

function populatePage() {
    fetch('http://localhost:8080/admin/data')
        .then(response => {
            return response.json();
        }).then(data => {
            data.products.sort((a, b) => {
                return a.id - b.id;
            });
            populateTableProducts(data.products);
            populateTableOrders(data.orders);
            document.querySelector('#products-button').click();
        })
}

function populateTableProducts(products) {
    const tableProducts = document.querySelector('#products-body');

    products.forEach(product => {
        row = document.createElement('tr');
        row.className = 'product-row';
        row.setAttribute('id', product.id);

        for (i = 1; i <= 7; i++) {
            td = document.createElement('td');
            td.className = i < 6? 'table-info': 'product-button';

            switch (i) {
                case 1:
                    td.innerHTML = product.id;
                    break;
                case 2:
                    td.innerHTML = product.name;
                    break;
                case 3:
                    td.innerHTML = product.author;
                    break;
                case 4:
                    td.innerHTML = product.price.toFixed(2) + ' лв.';
                    td.setAttribute('id', 'price');
                    break;
                case 5:
                    td.innerHTML = product.timesBought;
                    break;
                case 6:
                    div = document.createElement('div');
                    div.className = 'table-button-holder';
                    button = document.createElement('button');
                    button.className = 'edit-button';
                    button.addEventListener('click', (event) => {
                        id = event.currentTarget.parentElement.parentElement.parentElement.getAttribute('id');
                        editProduct(id);
                    }, false);
                    button.innerHTML = 'Редактирай';
                    div.appendChild(button);
                    td.appendChild(div);
                    break;
                case 7:
                    div = document.createElement('div');
                    div.className = 'table-button-holder';
                    button = document.createElement('button');
                    button.className = 'delete-button';
                    button.addEventListener('click', (event) => {
                        id = event.currentTarget.parentElement.parentElement.parentElement.getAttribute('id');
                        removeProduct(id);
                    }, false);
                    button.innerHTML = 'Изтрий';
                    div.appendChild(button);
                    td.appendChild(div);
                    break;
            }

            row.appendChild(td);
        }

        tableProducts.appendChild(row);
    })
}

function populateTableOrders(orders) {
    const tableOrders = document.querySelector('#orders-body');

    orders.forEach(order => {
        row = document.createElement('tr');
        row.className = 'order-row';
        row.setAttribute('id', order.id);

        for (i = 1; i <= 7; i++) {
            td = document.createElement('td');
            td.className = 'table-info';

            switch (i) {
                case 1:
                    td.innerHTML = order.id;
                    break;
                case 2:
                    td.innerHTML = order.productName;
                    break;
                case 3:
                    td.innerHTML = order.buyerName;
                    break;
                case 4:
                    td.innerHTML = order.buyerAddress;
                    break;
                case 5:
                    date = new Date(order.date)
                    td.innerHTML = date.toLocaleString();
                    td.setAttribute('id', 'date');
                    break;
                case 6:
                    td.innerHTML = order.buyAmount;
                    break;
                case 7:
                    td.innerHTML = order.totalPrice.toFixed(2) + ' лв.';
                    td.setAttribute('id', 'price');
                    break;
            }

            row.appendChild(td);
        }

        tableOrders.appendChild(row);
    })
}

function showTab(event, tab) {
    tabcontent = document.querySelectorAll('.tab-content');
    for (i = 0; i < tabcontent.length; i++)
        tabcontent[i].style.display = 'none';

    tablinks = document.querySelectorAll('.tab-link');
    for (i = 0; i < tablinks.length; i++)
        tablinks[i].className = tablinks[i].className.replace(' active', '');

    document.querySelector('#' + tab).style.display = "block";
    event.currentTarget.className += ' active';
}

function editProduct(id) {
    fetch('http://localhost:8080/admin/' + id)
        .then(response => {
            return response.json();
        }).then(data => {
            populateProductInfoForm(data);
        })
}

function addProduct() {
    requestBody = {
        name: document.querySelector('#name-input').value,
        author: document.querySelector('#author-input').value,
        shortDescription: document.querySelector('#short-description-input').value,
        description: document.querySelector('#description-input').value,
        pageCount: document.querySelector('#pageCount-input').value,
        price: document.querySelector('#price-input').value
    }

    fetch('http://localhost:8080/admin/products/add', {
        method: 'POST',
        body: JSON.stringify(requestBody),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(() => {
        location.reload(true);
    })
}

function saveChanges() {
    requestBody = {
        name: document.querySelector('#name-input').value,
        author: document.querySelector('#author-input').value,
        shortDescription: document.querySelector('#short-description-input').value,
        description: document.querySelector('#description-input').value,
        pageCount: document.querySelector('#pageCount-input').value,
        price: document.querySelector('#price-input').value
    }

    fetch('http://localhost:8080/admin/' + id + '/update', {
        method: 'PUT',
        body: JSON.stringify(requestBody),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(() => {
        location.reload(true);
    })
}

function populateProductInfoForm(product) {
    document.querySelector('#name-input').value = product.name;
    document.querySelector('#author-input').value = product.author;
    document.querySelector('#price-input').value = product.price;
    document.querySelector('#pageCount-input').value = product.pageCount;
    document.querySelector('#short-description-input').value = product.shortDescription;
    document.querySelector('#description-input').value = product.description;
}

function removeProduct(id) {
    fetch('http://localhost:8080/admin/' + id + '/delete', {
        method: 'DELETE'
    }).then(() => {
        location.reload(true);
    })
}