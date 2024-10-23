# Càrrega de les biblioteques necessàries
library(ggplot2)

# Noves dades
data <- data.frame(
  Felicitat_0 = c(647, 427, 594, 456, 358, 201, 350, 161, 364, 435),
  Felicitat_5 = c(785, 748, 492, 554, 740, 413, 560, 429, 435, 451),
  Felicitat_10 = c(925, 615, 410, 742, 648, 476, 548, 443, 484, 523),
  Felicitat_15 = c(668, 695, 409, 514, 701, 643, 713, 424, 479, 516),
  Felicitat_20 = c(525, 736, 599, 837, 662, 602, 773, 491, 464, 477)
)

# Crear un dataframe llarg per a graficar
data_long <- data.frame(
  Felicitat = c(0, 5, 10, 15, 20),
  Preu = colMeans(data)
)

# Crear el gràfic amb totes les dades
ggplot(data_long, aes(x = Felicitat, y = Preu)) +
  geom_line(color = "blue") +
  geom_point(size = 3, color = "black") +
  labs(title = "Evolucio del Temps a mesura que augmenta la Felicitat",
       x = "Ponderacio de la Felicitat",
       y = "Temps") +
  scale_y_continuous(limits = c(0, NA)) +  # Ajustar el límit inferior de l'eix Y a 0
  theme_minimal()
