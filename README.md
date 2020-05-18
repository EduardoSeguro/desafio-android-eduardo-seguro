# Desafio Android Marvel API

Aplicativo de informações de personagens da Marvel utilizando a API fornecida por eles.

Considerações:
- Utilizei uma arquitetura MVVM por considerar ela mais robusta para projetos de médio e grande porte;
- Criei alguns cenários de testes instrumentados e um caso de teste unitário em uma função crítica do sistema;
- Devido ao limite de requests da própria API, pode ser que o sistema fique inacessível depois de utilizar por um tempo. Caso isso aconteça, só será possível acessar o APP no outro dia;
- Para a avaliação, só mantenho em cache os dados carregados enquanto o APP está aberto. Ao fechar e abrir o APP, as requests são feitas novamente.