# MODO DE USO

Este software se hizo con el objetivo de cumplir con el requerimiento de la prueba para ingreso a la empresa Rhiscom. Dicho software consiste en manejo de una tabla de promociones con unos productos asociados.

Para poder implementar el software, inicialmente verifique que tenga instalado en su sistema operativoo Docker, docker-compose, Java 17 y Git.

Luego abra una consola en la ubicación de su preferencia y digite el siguiente comando:

```bash
    git clone https://github.com/efsiul/productos_promociones.git
```

posteriormente digite el siguiente comando:

```bash
    cd productos_promociones
```

Ya dentro de la carpeta, permita correr docker-compose con el siguiente comando

```bash
    docker-compose up --build -d 
```

Se ejecutara el docker-compose y se construira la base de datos en PostgreSQL

Inicialice el proyecto con el editor de codigo de su preferencia, este ultimo debe permitir correr SpringBoot

---

Abra Postman o cualquier herramienta que permita implementar metods http e ingrese en el metodo 'POST' el siguiente endpoint:
    <http://localhost:8080/promotions/load>

Se cargara la tabla de promociones con el archivo data/promotion.json, donde se encuentran las promociones determinadas.

Puede listar dichos productos a traves del enpoint con metodo GET:
    <http://localhost:8080/promotions>


Puede ingresar los diferentes productos, de forma individual en el siguiente endpoint con metodo POST:
    <http://localhost:8080/items/create>

con las siguientes caracteristicas:

```json
    {
        "itemCode": "2627610",
        "name": "ProductoJ",
        "price": 1.05
    }
```

Puede consultar la lista de productos ingresados en el siguiente endpoint con metodo GET:
    <http://localhost:8080/items/getAll>

para validación promociones puede ingrear al siguiente endpoint con metodo POST:
    <http://localhost:8080/items/validatePromotions>

la estructura de los datos debe ser la siguiente:

```json
    [
        {
            "itemCode": "2627601",
            "name": "ProductoA",
            "price": 8.90
        },
        {
            "itemCode": "2627602",
            "name": "ProductoB",
            "price": 12.50
        }
    ]
```
