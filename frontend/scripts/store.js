document.addEventListener('DOMContentLoaded', function() {
    populateStore();
}, false)

function populateStore() {
    const request = new Request('http://localhost:8080/store/products');
    const storeBody = document.querySelector('.store-body');

    fetch(request)
        .then(response => response.json())
        .then(data => {
            for (let rowIdx = 0; rowIdx < data.length; rowIdx++) {
                const row = data[rowIdx];
                tablerow = document.createElement('tr');
                tablerow.className = 'store-row';
                tablerow.setAttribute('id', 'row'+ (rowIdx+1));
                storeBody.appendChild(tablerow);

                for (let item = 0; item < row.length; item++) {
                    const element = row[item];
                    
                    let td = document.createElement('td');
                    td.className = 'store-item';
                    tablerow.appendChild(td);
                    
                    let a = document.createElement('a');
                    a.className = 'store-tile';
                    a.setAttribute('href', 'product.html');
                    a.setAttribute('id', element.id);
                    a.addEventListener('click', event => {
                        setClickedId(event);
                    }, false);
                    td.appendChild(a);
                    
                    let productContainer = document.createElement('div');
                    productContainer.className = 'product-container';
                    a.appendChild(productContainer);
                    
                    let productImageHolder = document.createElement('div');
                    productImageHolder.className = 'product-image-holder';
                    productContainer.appendChild(productImageHolder);
                    
                    let productInfoHolder = document.createElement('div')
                    productInfoHolder.className = 'product-info-holder';
                    productContainer.appendChild(productInfoHolder);

                    let productImage = document.createElement('img');
                    productImage.className = 'product-image';
                    productImage.setAttribute('src', '../resources/images/products/'+element.id+'.jpg');
                    productImage.setAttribute('alt', element.name);
                    productImageHolder.appendChild(productImage);

                    let bookName = document.createElement('h4');
                    bookName.className = 'book-name';
                    bookName.innerHTML = element.name;
                    productInfoHolder.appendChild(bookName);

                    let bookAuthor = document.createElement('p');
                    bookAuthor.className = 'book-author';
                    bookAuthor.innerHTML = element.author;
                    productInfoHolder.appendChild(bookAuthor);

                    let description = document.createElement('p');
                    description.className = 'short-description';
                    description.innerHTML = element.shortDescription;
                    productInfoHolder.appendChild(description);

                    let priceBadge = document.createElement('p');
                    priceBadge.className = 'price-badge';
                    priceBadge.innerHTML = element.price.toFixed(2) + ' лв.';
                    productInfoHolder.appendChild(priceBadge);
                }
            }
        })
}

function setClickedId(event) {
    sessionStorage.setItem('Id', event.currentTarget.getAttribute('id'));
}