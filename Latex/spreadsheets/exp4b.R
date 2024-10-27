# Dades
data <- read.csv("exp4b.csv", sep = ";")
# Seleccionar columnes dels temps per paquets
temps <- data[, c("X100", "X150", "X200", "X250", "X300", "X350", "X400")]

# Estadístiques bàsiques: mitjana i desviació estàndard
mean_times <- colMeans(temps)
sd_times <- apply(temps, 2, sd)
cv_times <- sd_times / mean_times
medianas <- lapply(temps, median)


str(mean_times)  # Comprova l'estructura de mean_times
print(names(mean_times))
names(mean_times) <- c(100, 150, 200, 250, 300, 350, 400)

# Reduir les marges del gràfic
par(mar=c(4, 4, 2, 2))

# 1. Gràfic de línies per mostrar la mitjana del temps d'execució
plot(names(mean_times), mean_times, type="o", col="blue",
     xlab="Nombre de paquets", ylab="Temps d'execució (ms)",
     main="Mitjana del temps d'execució per nombre de paquets")
grid()

# 2. Gràfic de barres amb barres d'error (error bars)
library(ggplot2)
temps_means <- data.frame(Paquets=names(mean_times), Mitjana=mean_times, SD=sd_times)
ggplot(temps_means, aes(x=Paquets, y=Mitjana)) +
  geom_bar(stat="identity", fill="skyblue") +
  geom_errorbar(aes(ymin=Mitjana-SD, ymax=Mitjana+SD), width=0.2, color="red") +
  labs(title="Temps d'execució amb barres d'error", x="Nombre de paquets", y="Temps (ms)")

# 3. Boxplot per comparar la distribució de temps d'execució per cada nombre de paquets
boxplot(temps, names=names(mean_times), xlab="Nombre de paquets", ylab="Temps d'execució (ms)",
        main="Distribució dels temps d'execució per nombre de paquets", col="lightblue")
grid()

# 4. Mostra les estadístiques
mean_times
sd_times
cv_times
medianas

