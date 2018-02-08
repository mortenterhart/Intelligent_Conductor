# Intelligent Conductor

**Used Design Patterns:** Builder, Bridge, Adapter

## Assignment of task
Ein Zug hat einen Waggon. Dieser Waggon hat auf der linken und rechten Seite je 25 Sitzplätze –
getrennt durch einen Gang. Das elektronische Ticket besteht aus den Informationen Reisender
(name und fingerprint), Datum, Klasse sowie Strecke von und nach. Ein Builder erstellt das Ticket
und registriert es in einem zentralen Repository. Zusätzlich wird das Ticket dem Reisenden auf das
MPhone gesandt. Das MPhone ist mit einem RFID-Chip ausgestattet. Der RFID-Chip existiert in den
Varianten EU und US. Der intelligente Zugbegleiter besucht sukzessive die belegten Sitzplätze und
überprüft die Tickets anhand der registrierten Tickets im zentralen Repository. Standardmäßig
liest der intelligente Zugbegleiter den RFID-Chip der Variante EU. Für das Auslesen des RFID-Chips
der Variante US nutzt der intelligente Zugbegleiter einen Adapter.
