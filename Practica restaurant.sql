use restaurant;

Select name from ingredients
inner join vendors on ingredients.vendorid = vendors.vendorid
where vendors.companyname = 'Veggies_R_Us';

Select name from ingredients 
inner join vendors on ingredients.vendorid = vendors.vendorid
where (vendors.companyname = 'Veggies_R_Us'or vendors.companyname = 'Spring Water Supply');

Select avg(price) from items
inner join madewith on items.itemid = madewith.itemid
inner join ingredients on madewith.ingredientid = ingredients.ingredientid
inner join vendors on ingredients.vendorid = vendors.vendorid
where companyname = 'Veggies_R_Us';

Select name from ingredients 
where ingredients.inventory < (select avg(inventory)from ingredients);

Select companyname from vendors
where referredby = (Select vendorid from vendors where companyname = 'Veggies_R_Us');

Select * from vendors;