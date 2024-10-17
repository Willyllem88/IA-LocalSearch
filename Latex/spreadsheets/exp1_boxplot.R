# Dades
move <- c(1006.41, 984.43, 779.85, 898.78, 903.76, 1008.90, 957.82, 978.06, 992.85, 1069.19)
swap <- c(1062.37, 1047.12, 836.83, 971.68, 986.67, 1043.78, 1011.53, 1008.93, 1039.82, 1172.32)
mvsw <- c(1005.24, 984.2, 779.73, 898.03, 903.01, 1008.38, 957.07, 977.72, 992.76, 1064.72)

# Crear el boxplot amb noms
boxplot(move, swap, mvsw, 
        main = "Boxplot del preu resultant de diferents operator sets", 
        names = c("Move", "Swap", "Move + Swap"),
        ylab = "Preu",
        col = "lightblue")

# Mostrar la cuadrícula
grid()
