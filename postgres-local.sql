SELECT * FROM EZ_FASTFOOD.ORDER;
SELECT * FROM EZ_FASTFOOD.CUSTOMER;
SELECT * FROM EZ_FASTFOOD.PRODUCT;

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
