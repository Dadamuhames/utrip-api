INSERT INTO admins VALUES (1, 'admin', '$2a$10$R3OBcmdSF.JASiNSO2vOeOEtvLP//L0JAv8cU20q/wDNJ2wPKq8ay', true, '2026-03-09 11:42:44.302119', '2026-03-09 11:42:44.195467');


--
-- Data for Name: agencies; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO agencies VALUES (1, 'msd', 'test', '$2a$10$sHyL0Stzx/MgRMJ4fGFXQO7YT6SvuF6opoboWo7TmIE.i2bZdXmam', NULL, '{"en": "Pearl of Tian Shan in one day", "ru": "Жемчужина Тянь-Шаня за один день", "uz": "Tyan-Shanning durdonasi bir kunda"}', true, '2026-03-08 10:17:48.142486', '2026-03-08 16:14:24.277665');
INSERT INTO agencies VALUES (2, 'AgencyOne', 'agency', '$2a$10$W9r8FxsZ04iOnb4R.9/Hke1Eph4v9adbGK3w28MMGFbcERntPH2pe', NULL, '{"en": "uzb", "ru": "uzb", "uz": "uzb"}', true, '2026-03-09 13:14:36.38439', '2026-03-09 13:14:36.269605');


--
-- Data for Name: agency_images; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO agency_images VALUES (1, 1, 'tour-images/1772984919905.jpg', '2026-03-08 16:14:24.242393', '2026-03-08 16:14:24.242429');


--
-- Data for Name: agency_infos; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO agency_infos VALUES (1, 1, 'string', '998953393588', '{"en": "Pearl of Tian Shan in one day", "ru": "Жемчужина Тянь-Шаня за один день", "uz": "Tyan-Shanning durdonasi bir kunda"}', 'some@gmail.com', '2026-03-08 16:14:24.19871', '2026-03-08 16:14:24.199205');


--
-- Data for Name: agency_videos; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO agency_videos VALUES (1, 1, 'tour-images/1772984919905.jpg', '2026-03-08 16:14:24.25034', '2026-03-08 16:14:24.250378');


--
-- Data for Name: applications; Type: TABLE DATA; Schema:  Owner: root
--



--
-- Data for Name: categories; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO categories VALUES (1, '{"en": "uzb", "ru": "uzb", "uz": "uzb"}', 'tour-images/1773061839657.jpg', '2026-03-09 13:13:48.637124', '2026-03-09 13:13:48.637177');


--
-- Data for Name: countries; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO countries VALUES (1, '{"en": "uzb", "ru": "uzb", "uz": "uzb"}', '2026-03-09 11:47:10.382629', '2026-03-09 11:47:10.382702');


--
-- Data for Name: regions; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO regions VALUES (1, '{"en": "tash1", "ru": "tash1", "uz": "tash1"}', 1, '2026-03-09 13:08:51.796216', '2026-03-09 13:09:29.961786');


--
-- Data for Name: reviews; Type: TABLE DATA; Schema:  Owner: root
--



--
-- Data for Name: schedules; Type: TABLE DATA; Schema:  Owner: root
--

--
-- Data for Name: tours; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO tours VALUES (2, 2, 1, '{"en": "One-day tour to Charvak Lake and Akbulak Waterfall", "ru": "Однодневная экскурсия в Чарвак и водопад Акбулак", "uz": "Chorvoq ko‘li va Oqbuloq sharsharasiga bir kunlik ekskursiya"}', 'tour-images/1773061839657.jpg', '{"en": "Pearl of Tian Shan in one day", "ru": "Жемчужина Тянь-Шаня за один день", "uz": "Tyan-Shanning durdonasi bir kunda"}', 'Чарвакское водохранилище → Акбулак, Бостанлыкский район, Ташкентская область', 14, 550000.00, '{"en": "Comfortable day trip from Tashkent: scenic Charvak reservoir, ancient petroglyphs and cascading Akbulak waterfall. Perfect for first-time visitors to Uzbekistan''s nature.", "ru": "Комфортная поездка из Ташкента: живописное Чарвакское водохранилище, древние петроглифы и каскадный водопад Акбулак. Идеально подходит для первого знакомства с природой Узбекистана.", "uz": "Toshkentdan qulay bir kunlik sayohat: manzarali Chorvoq suv ombori, qadimiy petrogliflar va kaskadli Oqbuloq sharsharası. O‘zbekiston tabiatiga birinchi marta tashrif buyuruvchilar uchun ideal."}', '{"en": "Bring comfortable sports shoes, windbreaker (spring/autumn), swimsuit (summer), water and snacks. Part of the road is mountainous.", "ru": "Берите удобную спортивную обувь, ветровку (весной/осенью), купальник (летом), воду и перекус. Дорога частично горная.", "uz": "Qulay sport poyabzali, shamolga qarshi kurtka (bahor/kuz), suzish kiyimi (yoz), suv va yegulik oling. Yo‘lning bir qismi tog‘li."}', '2026-03-22', '2026-03-22', 1, '2026-03-09 13:24:13.929065', '2026-03-09 13:24:13.929127');



INSERT INTO inclusions VALUES (1, '{"en": "Transportation (comfortable minivan / SUV)", "ru": "Транспорт (комфортабельный минивен / внедорожник)", "uz": "Transport (qulay miniven / yo‘ltanlamas)"}', 2, true, '2026-03-09 13:24:13.98515', '2026-03-09 13:24:13.985169');
INSERT INTO inclusions VALUES (2, '{"en": "Russian / Uzbek speaking guide", "ru": "Услуги русскоязычного / узбекоязычного гида", "uz": "Rus / o‘zbek tilida so‘zlashuvchi gid"}', 2, true, '2026-03-09 13:24:13.995683', '2026-03-09 13:24:13.995699');
INSERT INTO inclusions VALUES (3, '{"en": "National style lunch", "ru": "Обед в национальном стиле", "uz": "Milliy uslubdagi tushlik"}', 2, true, '2026-03-09 13:24:13.998349', '2026-03-09 13:24:13.998365');
INSERT INTO inclusions VALUES (4, '{"en": "1 liter drinking water per person", "ru": "Питьевая вода 1 л / человек", "uz": "Kishi boshiga 1 litr ichimlik suvi"}', 2, true, '2026-03-09 13:24:14.000415', '2026-03-09 13:24:14.00043');
INSERT INTO inclusions VALUES (5, '{"en": "Personal expenses, souvenirs, extra activities", "ru": "Личные расходы, сувениры, дополнительные активности", "uz": "Shaxsiy xarajatlar, suvenirlar, qo‘shimcha faoliyatlar"}', 2, false, '2026-03-09 13:24:14.002377', '2026-03-09 13:24:14.002391');


--


INSERT INTO schedules VALUES (3, 2, 3, '{"en": "11:00 – Sary-Chechek Petroglyphs", "ru": "11:00 – Петроглифы Сары-Чечек", "uz": "11:00 – Sari-Chechak petrogliflari"}', '{"en": "Viewing ancient rock drawings (~3000 years old)", "ru": "Осмотр древних наскальных рисунков (около 3000 лет)", "uz": "Qadimiy qoya rasmlarini ko‘rish (~3000 yillik)"}', '2026-03-09 13:24:13.951931', '2026-03-09 13:24:13.951951');
INSERT INTO schedules VALUES (4, 2, 1, '{"en": "12:30 – Akbulak Waterfall + lunch", "ru": "12:30 – Водопад Акбулак + обед", "uz": "12:30 – Oqbuloq sharsharasi + tushlik"}', '{"en": "Swimming (optional), national outdoor lunch", "ru": "Купание (по желанию), национальный обед на природе", "uz": "Suzish (xohish bo‘yicha), tabiatda milliy tushlik"}', '2026-03-09 13:24:13.958105', '2026-03-09 13:24:13.958124');
INSERT INTO schedules VALUES (5, 2, 2, '{"en": "16:00 – Return to Tashkent", "ru": "16:00 – Выезд обратно в Ташкент", "uz": "16:00 – Toshkentga qaytish"}', '{"en": "Approximate arrival 18:30–19:30", "ru": "Ориентировочное прибытие 18:30–19:30", "uz": "Taxminiy kelish 18:30–19:30"}', '2026-03-09 13:24:13.960822', '2026-03-09 13:24:13.960837');


--
-- Data for Name: tour_images; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO tour_images VALUES (3, 2, 'tour-images/1773061839657.jpg', '2026-03-09 13:24:13.948549', '2026-03-09 13:24:13.948577');
INSERT INTO tour_images VALUES (4, 2, 'tour-images/1773061839657.jpg', '2026-03-09 13:24:13.950105', '2026-03-09 13:24:13.950115');




--
-- Data for Name: uploaded_files; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO uploaded_files VALUES (1, '1772984919905.jpg', 'tour-images', '2026-03-10 15:48:39.935304');
INSERT INTO uploaded_files VALUES (2, '1773061830058.jpg', 'tour-images', '2026-03-11 13:10:30.097475');


--
-- Data for Name: users; Type: TABLE DATA; Schema:  Owner: root
--

INSERT INTO users VALUES (1, 'sdsadasd', '998953393588', NULL, 5936852964, '$2a$10$IUQIolndH8c/xLQSELs6J.lG2cM4qnw3.rDeFvFEilCzOy7jCd2we', true, '2026-03-09 18:15:52.470967', '2026-03-09 18:15:52.483664');





