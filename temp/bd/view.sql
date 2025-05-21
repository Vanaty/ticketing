-- Active: 1741940302403@@127.0.0.1@5432@ticketing

CREATE OR REPLACE VIEW vol_summary AS
SELECT 
    v.*,
    COALESCE(sum(s.nbr), 0) as nbrSiege,
    COALESCE(sum(r.nbrplaces),0) as nbrSiegeReservee,
    COALESCE(sum(s.nbr) - sum(r.nbrplaces) + sum(r.nbrplacesannuller),0) as nbrSiegeLibre,
    COALESCE(sum(r.prixtotal),0) as prixtotal
FROM 
    Vol v
LEFT JOIN
    reservation r ON r.id_vol = v.id
LEFT JOIN 
    Siege s ON s.id_avion = v.id_avion 
GROUP BY v.id,v.id_avion,v.id_ville_dep,v.id_ville_arr,v.datedepart,v.heuredepart,v.duree;

DROP VIEW IF EXISTS vol_summary CASCADE;