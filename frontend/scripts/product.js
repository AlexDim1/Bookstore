document.addEventListener('DOMContentLoaded', function() {
    populatePage();
}, false)

function populatePage() {
    const request = new Request('/store/products/' + sessionStorage.getItem('Id'));

    fetch(request)
        .then(response => response.json())
        .then(data => {
            document.querySelector('title').innerHTML = data.name;
            document.querySelector('.product-photo').setAttribute('src', '../resources/images/products/' + data.id + '.jpg');
            document.querySelector('#price').innerHTML = data.price.toFixed(2) + ' лв.';
            document.querySelector('.product-name').innerHTML = data.name;
            document.querySelector('#author').innerHTML = data.author;
            document.querySelector('#description').innerHTML = data.description;
            document.querySelector('#page-count').innerHTML = data.pageCount;

            if(data.reviews.length > 0) {
                document.querySelector('.reviews-container').removeChild(document.querySelector('#reviews-message'));
            }

            data.reviews.forEach(element => {
                reviewItem = document.createElement('div');
                reviewItem.className = 'review-item';

                reviewContent = document.createElement('p');
                reviewContent.className = 'review-content';
                reviewContent.innerHTML = element.content;
                reviewItem.appendChild(reviewContent);

                date = new Date(element.date);
                reviewDate = document.createElement('p');
                reviewDate.className = 'review-date';
                reviewDate.innerHTML = date.toLocaleString();
                reviewItem.appendChild(reviewDate);

                document.querySelector('#reviews-heading').after(reviewItem);
            });
        })
}

function buyProduct() {
    requestBody = {
        buyAmount: document.querySelector('#quantity').value,
        buyerName: document.querySelector('#name-input').value,
        buyerAddress: document.querySelector('#address-input').value
    };

    fetch('/store/products/' + sessionStorage.getItem('Id') + '/buy', {
        method: 'POST',
        body: JSON.stringify(requestBody),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(response => {
        return response.json();
    }).then(data => {
        createBuyMessage(data.message, data.type);
        document.querySelector('.buy-form').reset();
    })
}

function postReview() {
    requestBody = {
        content: document.querySelector('#new-review-content').value
    };

    fetch('/store/products/' + sessionStorage.getItem('Id') + '/reviews/add', {
        method: 'POST',
        body: JSON.stringify(requestBody),
        headers: {
            'Content-type': 'application/json'
        }
    }).then(response => {
        if (!response.ok)
            return response.json();

        location.reload(true);
    }).then(data => {
        alert(data.message);
    })
}

function createBuyMessage(message, type) {
    container = document.querySelector('.photo-and-buy-form-container');

    if(document.querySelector('p[class*="-error"]') !== null) {
        p = document.querySelector('p[class*="-error"]');
        p.className = 'buy-message-' + type;
        p.innerHTML = message;
        return;
    } else if (document.querySelector('p[class*="-success"]') !== null) {
        p = document.querySelector('p[class*="-success"]');
        p.className = 'buy-message-' + type;
        p.innerHTML = message;
        return;
    }
        
    buyMessageContainer = document.createElement('div');
    buyMessageContainer.className = 'buy-message-container';

    buyMessage = document.createElement('p');
    buyMessage.className = 'buy-message-' + type;
    buyMessage.innerHTML = message;
    buyMessageContainer.appendChild(buyMessage);

    container.after(buyMessageContainer);
}