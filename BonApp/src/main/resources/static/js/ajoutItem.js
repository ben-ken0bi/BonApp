//Création d'un bouton et abonnement à une fonction
var ajouterBouton = document.getElementById('ajoutItem-button');
ajouterBouton.addEventListener('click', addItem);

var sauvegarderBouton = document.getElementById('save-button');
sauvegarderBouton.addEventListener('click', saveList);

var supprimerItemBouton = document.getElementById('clear-completed-button');
supprimerItemBouton.addEventListener('click', clearCompletedItems);


//fonction pour ajouter un item
function addItem() {
    var itemText =itemEntryIngredient.options[itemEntryIngredient.selectedIndex].text;
    var itemQuantite = itemEntryQuantite.value;
    var itemMesure = itemEntryMesure.options[itemEntryMesure.selectedIndex].text;

    newItem(itemText, itemQuantite, itemMesure);
}

//Implémentation de la liste d'item
var itemEntryIngredient = document.getElementById('item-entry-ingredient');
var itemEntryQuantite = document.getElementById('item-entry-quantite');
var itemEntryMesure = document.getElementById('item-entry-mesure');
var itemList = document.getElementById('item-list');

//fonction pour ajouter un nouvel item à la liste
function newItem(itemText, itemQuantite, itemMesure) {
    var item = document.createElement('li');
    var itemAjouteText = document.createTextNode(itemText);
    var itemAjouteQuantite = document.createTextNode(itemQuantite);
    var itemAjouteMesure = document.createTextNode(itemMesure);

    item.appendChild(itemAjouteText);
    item.appendChild(itemAjouteQuantite);
    item.appendChild(itemAjouteMesure);


    itemList.appendChild(item);
    item.addEventListener('click', toggleItemState);
}

function toggleItemState() {
    if (this.classList.contains('completed')) {
        this.classList.remove('completed');
    } else {
        this.classList.add('completed');
    }
}

//Pour supprimer des items
function clearCompletedItems() {
    var completedItems = itemList.getElementsByClassName('completed');

    while (completedItems.length > 0) {
        completedItems.item(0).remove();
    }
}

//Pour sauvegarder
var myArray = [];
myArray.push('something to store');
myArray.push('something else to store');
alert(myArray[0]);

var itemInfo = {
    'task': 'Ajouter l\'ingrédient',
    'completed': false,
};

function saveList() {
    var toDos = [];

    for (var i = 0; i < itemList.children.length; i++) {
        var item = itemList.children.item(i);

        var toDoInfo = {
            'task': item.innerText,
            'completed': item.classList.contains('completed'),
        };

        toDos.push(itemInfo);

    }

    localStorage.setItem('toDos', JSON.stringify(toDos));
}

//Pour tout supprimer
function emptyList() {
    var items = itemList.children;
    while (items.length > 0) {
        items.item(0).remove();
    }
}