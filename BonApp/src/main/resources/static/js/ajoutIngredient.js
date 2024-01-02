document.getElementById('ajouterIngredient').addEventListener('click', function() {
    var lastSelect = document.querySelector('.ingredientsField:last-child');

    // Vérifier si une option a été choisie dans le dernier select
    if (lastSelect && lastSelect.value) {
        var container = document.getElementById('ingredientSelectDiv');
        var originalSelect = document.querySelector('.');
        var newSelect = originalSelect.cloneNode(true);

        newSelect.removeAttribute('required');
        newSelect.value = ''; // Réinitialiser la valeur sélectionnée pour le nouveau select
        container.appendChild(newSelect);

        resetSelectOptions();
        updateIngredientSelectOptions();
    } else {
        alert('Veuillez sélectionner une option dans le dernier acteur avant d\'en ajouter un nouveau.');
    }
});

function resetSelectOptions() {
    var allSelects = document.querySelectorAll('.ingredientsField');
    allSelects.forEach(function(select) {
        var options = select.options;
        for (var i = 0; i < options.length; i++) {
            options[i].disabled = false;
        }
    });
}

function updateIngredientSelectOptions() {
    var allSelects = document.querySelectorAll('.ingredientsField');
    var selectedValues = [];

    // Récupérer toutes les valeurs sélectionnées
    allSelects.forEach(function(select) {
        if (select.value) {
            selectedValues.push(select.value);
        }
    });

    // Mettre à jour les options de chaque select
    allSelects.forEach(function(select) {
        var options = select.options;
        for (var i = 0; i < options.length; i++) {
            // Désactiver l'option si elle a été sélectionnée dans un autre select
            // mais laisser l'option actuelle activée
            if (select.value !== options[i].value) {
                options[i].disabled = selectedValues.includes(options[i].value);
            }
        }
    });
}

// Appeler updateActorSelectOptions au chargement de la page pour initialiser les sélections
document.addEventListener('DOMContentLoaded', updateIngredientSelectOptions);
