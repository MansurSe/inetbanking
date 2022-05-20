API для Интернет-банка

Будут доступны следующие операции:

    Узнать баланс по ID пользователя;
    Снятие заданной суммы с баланса пользователя;
    Пополнение баланса на заданную сумму;
    Отобразить список операций за выбранный период;
    Перевести заданную сумму другому пользователю.

Ответ выдается в виде JSON.



Операции API:
/getBalance: получение баланса абонента по id;

Принимает параметры 	ID пользователя

пример: 
http://localhost:8080/getBalance/{id}



Снятие баланса абонента 
/takeMoney


Пример тела запроса:
{
"id": "2",
"balance": "1"
}



Пополнение баланса абонента. 
/putMoney

Пример тела запроса:
{
"id": "2",
"balance": "1"
}

Структура БД 
![image](https://user-images.githubusercontent.com/84962796/169588932-c5cadea0-9bdb-4f62-b908-29023602bcc9.png)

