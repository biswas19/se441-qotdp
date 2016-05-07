databaseChangeLog = {

    changeSet(author: "pbiswas5 (generated)", id: "1462052725885-1") {
        createTable(tableName: "quote_pipee") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "quote_pipeePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "text", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }
}
