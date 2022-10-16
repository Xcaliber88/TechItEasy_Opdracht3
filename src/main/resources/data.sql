INSERT INTO televisions (id, type, brand, name, price, available_size, refresh_rate, screen_type, screen_quality, smart_tv, wifi, voice_control, hdr, bluetooth, ambilight, original_stock,
                         sold) VALUES (1001,'NH3216SMART', 'Nikkei','HD smart TV',159,32 ,100 ,'LED', 'HD ready', true, true, false, false, false, false, 235885, 45896),
                                      (1002,'NH4216SMART', 'Nikkei','HD smart TV',259,42 ,100 ,'LED', 'HD ready', true, true, false, false, false, false, 245885, 40896);
INSERT INTO remote_controllers (id, compatible_with, battery_type, name, brand, price, original_stock ) VALUES (2001, 'Nikkei', 'AAA', 'universal remote','Samsung', 24.95, 46),
                                                                                                               (2002, 'Nikkei', 'AAA', 'all brand remote', 'Philips', 23.95,31);
INSERT INTO wall_brackets (id, size, adjustable, name, price) VALUES (3001,'42 INCH', true, 'Vogel', 19.95 ),
                                                                     (3002, '54 INCH',true, 'Vevor', 29.95);
INSERT INTO ci_modules (id, name, type, price) VALUES (4001,'Quantis', 'interactive Ci',39.95),
                                                      (4002, 'Quantis', 'interactive Ci',29.95);
