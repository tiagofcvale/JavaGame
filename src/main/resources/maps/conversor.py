# Caminhos dos arquivos
entrada = "C:\\Users\\tiago\\Desktop\\Projects\\javagame\\src\\main\\resources\\maps\\world01.txt"
saida = "C:\\Users\\tiago\\Desktop\\Projects\\javagame\\src\\main\\resources\\maps\\world02.txt"

# Função para processar os números
def processar_numero(numero):
    return f"1{int(numero)}".zfill(2)

# Abrir o arquivo de entrada e ler linhas
with open(entrada, "r") as f:
    linhas = f.readlines()

# Processar cada linha
linhas_processadas = []
for linha in linhas:
    numeros = linha.strip().split()
    nova_linha = [processar_numero(n) for n in numeros]
    linhas_processadas.append(" ".join(nova_linha))

# Escrever no arquivo de saída
with open(saida, "w") as f:
    f.write("\n".join(linhas_processadas))

print("Arquivo convertido com sucesso!")
