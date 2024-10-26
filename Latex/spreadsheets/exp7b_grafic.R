# Càrrega de les biblioteques necessàries
library(ggplot2)

# Noves mitjanes
mitjanes <- c(289, 377.5, 307.5, 400.5,	257)

# Crear un dataframe llarg per a graficar
data_long <- data.frame(
  Felicitat = c(0, 5, 10, 15, 20),
  Preu = mitjanes
)

# Crear el gràfic amb les noves mitjanes
ggplot(data_long, aes(x = Felicitat, y = Preu)) +
  geom_line(color = "blue") +
  geom_point(size = 3, color = "black") +
  labs(title = "Evolucio del Temps a mesura que augmenta la Felicitat amb SA",
       x = "Ponderacio de la Felicitat",
       y = "Temps") +
  scale_y_continuous(limits = c(0, NA)) +  # Ajustar el límit inferior de l'eix Y a 0
  theme_minimal()
