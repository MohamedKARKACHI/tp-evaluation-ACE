#!/bin/bash

# Script pour exécuter l'application de gestion de l'état civil

echo "=========================================="
echo "Application Gestion de l'État Civil"
echo "=========================================="
echo ""

# Vérifier si Maven est installé
if ! command -v mvn &> /dev/null
then
    echo "❌ Maven n'est pas installé. Veuillez installer Maven."
    exit 1
fi

echo "✓ Maven est installé"
echo ""

# Vérifier si MySQL est en cours d'exécution
if ! command -v mysql &> /dev/null
then
    echo "⚠️  MySQL n'est pas trouvé dans le PATH"
    echo "   Assurez-vous que MySQL est installé et en cours d'exécution"
else
    echo "✓ MySQL est disponible"
fi

echo ""
echo "Compilation du projet..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "❌ Erreur lors de la compilation"
    exit 1
fi

echo ""
echo "✓ Compilation réussie"
echo ""
echo "Exécution de l'application..."
echo "=========================================="
echo ""

mvn exec:java -Dexec.mainClass="ma.projet.App"

echo ""
echo "=========================================="
echo "Fin de l'exécution"
echo "=========================================="
