SELECT SUM(claimed_charge) FROM document WHERE status = 'EXPORTED';

SELECT insured_name, insured_address, claimed_charge FROM document d INNER JOIN batch b ON d.batch_id = b.id WHERE d.status = 'TO_REPRICE' AND b.customer_id IN (1, 2);

Link : https://sqlfiddle.com/mysql/online-compiler?id=25c2a0ac-4e31-4019-950b-86f752fad3c1