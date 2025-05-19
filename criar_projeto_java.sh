#!/bin/bash

# Perguntar nome do projeto
read -p "Digite o nome do projeto Java: " PROJETO

# Se o nome for vazio, sair
if [ -z "$PROJETO" ]; then
  echo "❌ Nome do projeto não pode estar vazio!"
  exit 1
fi


# Criar estrutura de diretórios
mkdir -p "$PROJETO"/{src,bin,.vscode}

# Criar Main.java simples
cat > "$PROJETO/src/Main.java" <<EOF
public class Main {
    public static void main(String[] args) {
        System.out.println("Projeto Java funcionando e pronto para debug!");
    }
}
EOF

# Criar tasks.json para compilar
cat > "$PROJETO/.vscode/tasks.json" <<EOF
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Compilar Java",
      "type": "shell",
      "command": "javac",
      "args": [
        "-g",
        "-d", "bin",
        "src/Main.java"
      ],
      "group": {
        "kind": "build",
        "isDefault": true
      },
      "problemMatcher": ["\$javac"]
    }
  ]
}
EOF

# Criar launch.json para debugar
cat > "$PROJETO/.vscode/launch.json" <<EOF
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Debug Main",
      "request": "launch",
      "mainClass": "Main",
      "classPaths": ["\${workspaceFolder}/bin"],
      "cwd": "\${workspaceFolder}"
    }
  ]
}
EOF

echo "✅ Projeto Java básico '$PROJETO' criado!"
echo "➡️  Abra com: code $PROJETO"
echo "🛠️  Compile com: Ctrl+Shift+B"
echo "🐞 Debugar com: F5"

