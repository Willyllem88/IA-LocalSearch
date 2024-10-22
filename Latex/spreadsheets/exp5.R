# Dades
data <- read.csv("exp5.csv", sep = ";")
# Seleccionar columnes dels temps per paquets
temps <- data[, c("X1.2", "X1.4", "X1.6", "X1.8", "X2.0")]

# Estadístiques bàsiques: mitjana i desviació estàndard
mean_times <- colMeans(temps)
sd_times <- apply(temps, 2, sd)
cv_times <- sd_times / mean_times

str(mean_times)  # Comprova l'estructura de mean_times
print(names(mean_times))
names(mean_times) <- c(1.2, 1.4, 1.6, 1.8, 2.0)

# Reduir les marges del gràfic
par(mar=c(4, 4, 2, 2))

# 1. Gràfic de línies per mostrar la mitjana del temps d'execució
plot(names(mean_times), mean_times, type="o", col="blue",
     xlab="Proporció del pes", ylab="Preu",
     main="Variació del preu per proporció del pes",
     ylim=c(0, max(mean_times)))
grid()

# 2. Gràfic de barres amb barres d'error (error bars)
library(ggplot2)
temps_means <- data.frame(Paquets=names(mean_times), Mitjana=mean_times, SD=sd_times)
ggplot(temps_means, aes(x=Paquets, y=Mitjana)) +
  geom_bar(stat="identity", fill="skyblue") +
  geom_errorbar(aes(ymin=Mitjana-SD, ymax=Mitjana+SD), width=0.2, color="red") +
  labs(title="Variació del preu per proporció del pes", x="Nombre de paquets", y="Temps (ms)")

# 3. Boxplot per comparar la distribució de temps d'execució per cada nombre de paquets
boxplot(temps, names=names(mean_times), xlab="Proporció del pes", ylab="Preu",
        main="Distribució del preu en funció de la proporció del pes")
grid()

# 4. Mostra les estadístiques
mean_times
sd_times
cv_times
