# Instalar ggplot2 si no está instalado
if (!require(ggplot2)) install.packages("ggplot2")

# Cargar las librerías necesarias
library(ggplot2)

# Cargar el archivo CSV
data <- read.csv("./exp9_k1.csv", sep = ";", dec = ",")

# Crear el gráfico
ggplot(data, aes(x = steps1, y = preu1, color = as.factor(lambda1))) +
  geom_line(size = 1) +  # Ajustar el grosor de la línea
  geom_point(size = 2) + # Ajustar el tamaño de los puntos
  labs(
    title = paste("Evolució del preu per nIt = 10000 i k = 1"),
    x = "Steps, nombre de passos",
    y = "Preu (€)",
    color = "lambda"
  ) +
  scale_color_manual(values = c("1" = "darkblue", "0.01" = "dodgerblue", "1e-04" = "lightblue")) +
  theme_minimal()
  