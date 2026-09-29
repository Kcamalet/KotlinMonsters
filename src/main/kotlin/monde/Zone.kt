package monde

import monstre.EspeceMonstre
import java.time.LocalDateTime

/**
 * Représente une zone du jeu (route, caverne, mer...).
 * Une zone est un endroit où le joueur peut chercher des monstres sauvages.
 * Les zones forment une chaîne de routes : chaque zone peut avoir une zone suivante et une zone précédente.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Expérience de base des monstres de la zone.
 * @property especesMonstres Liste des espèces de monstres que l'on peut rencontrer dans la zone.
 * @property zoneSuivante Zone suivante, ou null s'il n'y en a pas.
 * @property zonePrecedente Zone précédente, ou null s'il n'y en a pas.
 */
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null,
) {
    // TODO faire la méthode genereMonstre()
    // TODO faire la méthode rencontreMonstre()
}