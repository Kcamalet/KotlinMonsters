package item

import dresseur.Entraineur

/**
 * Représente un badge, un sous-type d'[Item].
 *
 * @property champion Le dresseur à battre pour obtenir le badge.
 */
class Badge(id: Int, nom: String, description: String, var champion: Entraineur) : Item(id, nom, description) {
}