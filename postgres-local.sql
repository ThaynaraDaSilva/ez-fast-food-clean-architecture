SELECT * FROM EZ_FASTFOOD.ORDER ORDER BY ID DESC;
SELECT * FROM EZ_FASTFOOD.CUSTOMER; 456.000.000-78

SELECT * FROM EZ_FASTFOOD.PAYMENT ORDER BY ID DESC;
SELECT * FROM EZ_FASTFOOD.PAYMENT WHERE ORDER_ID = '179';

146		10.0	PENDING		179
146	2024-09-21 00:21:37.492701	10.0	OK		179 - POS PAGAMENTO

ALTER TABLE EZ_FASTFOOD.ORDER ADD COLUMN order_number VARCHAR(255);

UPDATE ez_fastfood.order 
SET order_number = LPAD(CAST(id AS VARCHAR), 4, '0') || ' ' || customer_name;


WITH SequencedOrders AS (
    SELECT 
        id, 
        customer_name, 
        ROW_NUMBER() OVER (ORDER BY id) - 1 AS seq_number
    FROM ez_fastfood.order
)
UPDATE ez_fastfood.order
SET order_number = LPAD(SequencedOrders.seq_number::text, 4, '0') || ' ' || SequencedOrders.customer_name
FROM SequencedOrders
WHERE ez_fastfood.order.id = SequencedOrders.id;

SELECT *
FROM ez_fastfood.order
WHERE DATE(order_time) = CURRENT_DATE
ORDER BY order_time DESC
LIMIT 1;

179		Anthony	2024-09-21 00:20:25.070291	WAITING_PAYMENT	10.0		0002 Anthony - REGISTRO DE ORDER
179		Anthony	2024-09-21 00:21:37.512699	RECEIVED	10.0		0002 Anthony - POS PAGAMENTO


DELETE FROM EZ_FASTFOOD.ORDER WHERE ID = '142';

SELECT * FROM ez_fastfood.order WHERE DATE(order_time) = CURRENT_DATE ORDER BY order_time DESC LIMIT 1


TRUNCATE TABLE EZ_FASTFOOD.ORDER_ITEMS;
TRUNCATE TABLE EZ_FASTFOOD.PAYMENT;
TRUNCATE TABLE EZ_FASTFOOD.ORDER CASCADE;