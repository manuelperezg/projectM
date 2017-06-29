databaseChangeLog = {

    changeSet(author: "HP (generated)", id: "1498496327565-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-2") {
        createTable(tableName: "CATEGORIAS") {
            column(name: "ID", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "descripcion", type: "CLOB")

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-3") {
        createTable(tableName: "ESTANDARES") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "archivo", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tipo_archivo", type: "VARCHAR(17)") {
                constraints(nullable: "false")
            }

            column(name: "verificacion_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-4") {
        createTable(tableName: "HORARIOS") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "fecha_final", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "fecha_inicio", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "matricula", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-5") {
        createTable(tableName: "LINEAS") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "planta_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-6") {
        createTable(tableName: "LISTA_VERIFICACION") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "clase_verificacion", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "mantenimiento_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "maquina_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tipo_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-7") {
        createTable(tableName: "MAQUINAS") {
            column(name: "ID", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "archivo_foto", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "descripcion", type: "CLOB")

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "linea_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "qr_code", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-8") {
        createTable(tableName: "MATERIALES") {
            column(name: "ID", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "archivo_foto", type: "VARCHAR(255)")

            column(name: "categoria_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-9") {
        createTable(tableName: "PIEZAS") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "archivo_foto", type: "VARCHAR(255)")

            column(name: "clasificacion", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "descripcion", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "frecuencia", type: "VARCHAR(18)") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "mantenimiento_planeado", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "numero", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "numero_eventos", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "se_repite", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "subsistema_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-10") {
        createTable(tableName: "PLANTAS") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "archivo_esquema", type: "VARCHAR(255)")

            column(name: "archivo_foto", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "direccion", type: "VARCHAR(255)")

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-11") {
        createTable(tableName: "PUNTOS") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "actividad", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "conjunto", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "descripcion", type: "CLOB") {
                constraints(nullable: "false")
            }

            column(name: "dia_semana", type: "VARCHAR(9)")

            column(name: "dispositivos", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "estado", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "foto_bueno", type: "VARCHAR(255)")

            column(name: "foto_malo", type: "VARCHAR(255)")

            column(name: "frecuencia", type: "VARCHAR(21)") {
                constraints(nullable: "false")
            }

            column(name: "horario", type: "VARCHAR(255)")

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "mes", type: "VARCHAR(10)")

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "punto", type: "INT")

            column(name: "requiere_malo", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "requiere_materiales", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "requiere_subsistema", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "responsable", type: "VARCHAR(10)") {
                constraints(nullable: "false")
            }

            column(name: "subsistema_id", type: "VARCHAR(255)")

            column(name: "tiempo", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "turno", type: "VARCHAR(10)")

            column(name: "verificacion_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-12") {
        createTable(tableName: "SESION") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "fecha_ingreso", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "id_user", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "sesion_no", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-13") {
        createTable(tableName: "SUBSISTEMAS") {
            column(name: "ID", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "archivo_foto", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "descripcion", type: "CLOB")

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "maquina_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-14") {
        createTable(tableName: "TIPO_MANTENIMIENTO") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "archivo_foto", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "descripcion", type: "CLOB")

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-15") {
        createTable(tableName: "TIPO_VERIFICACION") {
            column(name: "ID", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "descripcion", type: "CLOB")

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-16") {
        createTable(tableName: "USERLG_ROLELG") {
            column(name: "userlg_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rolelg_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-17") {
        createTable(tableName: "evento") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "eventoPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "end_time", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "is_recurring", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "recur_count", type: "INT")

            column(name: "recur_interval", type: "INT")

            column(name: "recur_type", type: "VARCHAR(255)")

            column(name: "recur_until", type: "timestamp")

            column(name: "start_time", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "titulo", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-18") {
        createTable(tableName: "evento_exclude_days") {
            column(name: "evento_id", type: "BIGINT")

            column(name: "exclude_days_date", type: "timestamp")
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-19") {
        createTable(tableName: "evento_recur_days_of_week") {
            column(name: "evento_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "recur_days_of_week_integer", type: "INT")
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-20") {
        createTable(tableName: "plantas_lineas") {
            column(name: "id_planta", type: "VARCHAR(255)")

            column(name: "linea_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-21") {
        createTable(tableName: "puntos_materiales") {
            column(name: "punto_materiales_id", type: "VARCHAR(255)")

            column(name: "material_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-22") {
        createTable(tableName: "rolelg") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolelgPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-23") {
        createTable(tableName: "rolelg_lineas") {
            column(name: "rolelg_lineas_id", type: "BIGINT")

            column(name: "linea_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-24") {
        createTable(tableName: "userlg") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userlgPK")
            }

            column(name: "account_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "archivo_foto", type: "VARCHAR(255)")

            column(name: "date_created", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)")

            column(name: "enabled", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "id_autor", type: "BIGINT")

            column(name: "last_updated", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "nombre", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "password_plain", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "telefono_movil", type: "VARCHAR(255)")

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "HP (generated)", id: "1498496327565-25") {
        addPrimaryKey(columnNames: "ID", constraintName: "CATEGORIASPK", tableName: "CATEGORIAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-26") {
        addPrimaryKey(columnNames: "ID", constraintName: "ESTANDARESPK", tableName: "ESTANDARES")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-27") {
        addPrimaryKey(columnNames: "ID", constraintName: "HORARIOSPK", tableName: "HORARIOS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-28") {
        addPrimaryKey(columnNames: "ID", constraintName: "LINEASPK", tableName: "LINEAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-29") {
        addPrimaryKey(columnNames: "ID", constraintName: "LISTA_VERIFICACIONPK", tableName: "LISTA_VERIFICACION")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-30") {
        addPrimaryKey(columnNames: "ID", constraintName: "MAQUINASPK", tableName: "MAQUINAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-31") {
        addPrimaryKey(columnNames: "ID", constraintName: "MATERIALESPK", tableName: "MATERIALES")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-32") {
        addPrimaryKey(columnNames: "ID", constraintName: "PIEZASPK", tableName: "PIEZAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-33") {
        addPrimaryKey(columnNames: "ID", constraintName: "PLANTASPK", tableName: "PLANTAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-34") {
        addPrimaryKey(columnNames: "ID", constraintName: "PUNTOSPK", tableName: "PUNTOS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-35") {
        addPrimaryKey(columnNames: "ID", constraintName: "SESIONPK", tableName: "SESION")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-36") {
        addPrimaryKey(columnNames: "ID", constraintName: "SUBSISTEMASPK", tableName: "SUBSISTEMAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-37") {
        addPrimaryKey(columnNames: "ID", constraintName: "TIPO_MANTENIMIENTOPK", tableName: "TIPO_MANTENIMIENTO")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-38") {
        addPrimaryKey(columnNames: "ID", constraintName: "TIPO_VERIFICACIONPK", tableName: "TIPO_VERIFICACION")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-39") {
        addPrimaryKey(columnNames: "userlg_id, rolelg_id", constraintName: "USERLG_ROLELGPK", tableName: "USERLG_ROLELG")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-40") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLELGAUTHORITY_COL", tableName: "rolelg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-41") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERLGUSERNAME_COL", tableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-42") {
        addForeignKeyConstraint(baseColumnNames: "mantenimiento_id", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_2ixc6ba0wq0yeodnx3lmq0fnk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "TIPO_MANTENIMIENTO")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-43") {
        addForeignKeyConstraint(baseColumnNames: "subsistema_id", baseTableName: "PIEZAS", constraintName: "FK_2xgmqbmukypnp1s3j8gy7v91y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SUBSISTEMAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-44") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "TIPO_VERIFICACION", constraintName: "FK_2yuqqk42ay8858w74quo23b3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-45") {
        addForeignKeyConstraint(baseColumnNames: "linea_id", baseTableName: "rolelg_lineas", constraintName: "FK_330jmeqtw5f4cl5uac4uf9eop", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LINEAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-46") {
        addForeignKeyConstraint(baseColumnNames: "maquina_id", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_35guspjpgy3i2ctpgkabed91f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MAQUINAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-47") {
        addForeignKeyConstraint(baseColumnNames: "categoria_id", baseTableName: "MATERIALES", constraintName: "FK_4jjmcc53j5a0kdipioxsn9w1y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "CATEGORIAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-48") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "userlg", constraintName: "FK_4oembf8oesd07tekssul4upl6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-49") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "HORARIOS", constraintName: "FK_5a62gb4a1mdbsxdlb1yk3yp8m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-50") {
        addForeignKeyConstraint(baseColumnNames: "linea_id", baseTableName: "MAQUINAS", constraintName: "FK_8rwjueosk6frhs9u8anuhlcqf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LINEAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-51") {
        addForeignKeyConstraint(baseColumnNames: "punto_materiales_id", baseTableName: "puntos_materiales", constraintName: "FK_930skh71k3tqf9x51svk7hx79", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PUNTOS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-52") {
        addForeignKeyConstraint(baseColumnNames: "tipo_id", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_9k4anxua924qfq26ft5u4s19r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "TIPO_VERIFICACION")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-53") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "MAQUINAS", constraintName: "FK_9vfncwxkyoq82svt8lrrrujb2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-54") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "TIPO_MANTENIMIENTO", constraintName: "FK_day70p01cpsk26fv7od96p5hu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-55") {
        addForeignKeyConstraint(baseColumnNames: "evento_id", baseTableName: "evento_recur_days_of_week", constraintName: "FK_dptf0r647p1yysxgwc56plt4e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "evento")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-56") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "PUNTOS", constraintName: "FK_e4iolq4lbpv4jwgb3447w8y39", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-57") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "rolelg", constraintName: "FK_elqghj2pp0rfqkuqdnrnaico3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-58") {
        addForeignKeyConstraint(baseColumnNames: "verificacion_id", baseTableName: "PUNTOS", constraintName: "FK_eq4htewduf1xj89g0w58yoh54", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LISTA_VERIFICACION")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-59") {
        addForeignKeyConstraint(baseColumnNames: "rolelg_lineas_id", baseTableName: "rolelg_lineas", constraintName: "FK_fyb2wnsav7n41100961ffa8l6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rolelg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-60") {
        addForeignKeyConstraint(baseColumnNames: "linea_id", baseTableName: "plantas_lineas", constraintName: "FK_g4lwyaffts5pfwus24wy66ywo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LINEAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-61") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "SUBSISTEMAS", constraintName: "FK_gbyvmfiqcag3rhdievf2myi66", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-62") {
        addForeignKeyConstraint(baseColumnNames: "material_id", baseTableName: "puntos_materiales", constraintName: "FK_gysruu8664aece5pjo5hj1g9a", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MATERIALES")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-63") {
        addForeignKeyConstraint(baseColumnNames: "evento_id", baseTableName: "evento_exclude_days", constraintName: "FK_kafiqbg82bo58vxdklf2n54tp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "evento")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-64") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "ESTANDARES", constraintName: "FK_kumg8javulgicqi7mg0p7f4ev", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-65") {
        addForeignKeyConstraint(baseColumnNames: "subsistema_id", baseTableName: "PUNTOS", constraintName: "FK_kw5r78qnag76agodar4fjvlml", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SUBSISTEMAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-66") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "PLANTAS", constraintName: "FK_l0ba9vm8mf8ys6phca3o2a3fe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-67") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "CATEGORIAS", constraintName: "FK_loptcxa0xiobxxuvfw5y9egwp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-68") {
        addForeignKeyConstraint(baseColumnNames: "planta_id", baseTableName: "LINEAS", constraintName: "FK_luj2hmq3oaies20twvllvh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PLANTAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-69") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "MATERIALES", constraintName: "FK_m0jf9bvjah3ge0rdq8iumsm3s", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-70") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "PIEZAS", constraintName: "FK_me7hdac0gs0101vawfeoyfgk5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-71") {
        addForeignKeyConstraint(baseColumnNames: "rolelg_id", baseTableName: "USERLG_ROLELG", constraintName: "FK_njvhvoi6k6hd61s7pl6oshjkk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rolelg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-72") {
        addForeignKeyConstraint(baseColumnNames: "userlg_id", baseTableName: "USERLG_ROLELG", constraintName: "FK_odoqthc1493antalg1m5x9754", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-73") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_pwufvoak8n90c6netur2v2yy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-74") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "LINEAS", constraintName: "FK_qi9jc3yot00l5utenoi7hcdfv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "userlg")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-75") {
        addForeignKeyConstraint(baseColumnNames: "verificacion_id", baseTableName: "ESTANDARES", constraintName: "FK_sj6ghps0bisamgqd8xrytdox9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LISTA_VERIFICACION")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-76") {
        addForeignKeyConstraint(baseColumnNames: "id_planta", baseTableName: "plantas_lineas", constraintName: "FK_t22ptpt219brhw4d8yq1kkg56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PLANTAS")
    }

    changeSet(author: "HP (generated)", id: "1498496327565-77") {
        addForeignKeyConstraint(baseColumnNames: "maquina_id", baseTableName: "SUBSISTEMAS", constraintName: "FK_t61sffoyp8bfi6gcmfsrurw8i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MAQUINAS")
    }
}
