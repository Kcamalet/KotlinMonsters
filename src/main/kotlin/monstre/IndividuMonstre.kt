package monstre

import dresseur.Entraineur

/**
 * Représente un individu monstre, c'est-à-dire un monstre avec lequel le joueur interagit
 * (monstre sauvage, monstre de l'équipe du joueur, monstre d'un autre dresseur).
 * Deux individus peuvent appartenir à la même espèce, par exemple deux Canaros.
 *
 * @property id Identifiant unique de l'individu.
 * @property nom Nom de l'individu.
 * @property espece Espèce à laquelle appartient l'individu.
 * @property entraineur Entraîneur du monstre, ou null si c'est un monstre sauvage.
 * @param expInit Expérience initiale du monstre.
 */
class IndividuMonstre(
    val id: Int,
    var nom: String,
    val espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit: Double,
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + (-2..2).random()
    var defense: Int = espece.baseDefense + (-2..2).random()
    var vitesse: Int = espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = espece.baseDefenseSpe + (-2..2).random()
    var pvMax: Int = espece.basePv + (-5..5).random()
    var potentiel: Double = (50..200).random() / 100.0

    /**
     * @property exp Expérience actuelle du monstre.
     */
    var exp: Double = 0.0
        get() = field
        set(value) {
            field = value
        }

    /**
     * @property pv Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv
        }
}