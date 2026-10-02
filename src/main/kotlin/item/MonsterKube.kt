package item

import monstre.IndividuMonstre
import joueur

/**
 * Représente un objet de capture de monstre.
 *
 * Le MonsterKube est l'équivalent d'une "pokéball" : il permet de tenter de capturer un monstre sauvage.
 * Cette classe hérite de [Item] et implémente l'interface [Utilisable].
 *
 * @property chanceCapture Pourcentage de chance (entre 0 et 100) de capturer le monstre.
 */
class MonsterKube(
    id: Int,
    nom: String,
    description: String,
    var chanceCapture: Double,
) : Item(id, nom, description), Utilisable {

    /**
     * Tente de capturer le monstre cible.
     *
     * @param cible Le monstre que l'on tente de capturer.
     * @return `true` si la capture a réussi, `false` sinon.
     */
    override fun utiliser(cible: IndividuMonstre): Boolean {
        println("Vous lancez le Monster Kube !")

        if (cible.entraineur != null) {
            println("Le monstre ne peut pas être capturé.")
            return false
        }

        val nbAleatoire = (0..100).random()

        if (nbAleatoire < this.chanceCapture) {
            println("Le monstre est capturé !")

            println("Renommer ${cible.nom} ?")
            val nouveauNom = readln()
            if (nouveauNom.isNotBlank()) {
                cible.nom = nouveauNom
            }

            if (joueur.equipeMonstre.size >= 6) {
                joueur.boiteMonstre.add(cible)
            } else {
                joueur.equipeMonstre.add(cible)
            }

            cible.entraineur = joueur
            return true
        } else {
            println("Presque ! Le Kube n'a pas pu capturer le monstre !")
            return false
        }
    }
}