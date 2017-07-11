databaseChangeLog = {

    changeSet(author: "1200 (generated)", id: "1499752368467-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-2") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-3") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-4") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-5") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-6") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-7") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-8") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-9") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-10") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-11") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-12") {
        createTable(tableName: "PUNTOS_RESPUESTA") {
            column(name: "ID", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "duracion", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "estado", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "foto_evidencia", type: "VARCHAR(255)")

            column(name: "fregistro", type: "timestamp")

            column(name: "observaciones", type: "CLOB")

            column(name: "punto_id", type: "VARCHAR(255)")

            column(name: "semana", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-13") {
        createTable(tableName: "ROLELG") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "ROLELGPK")
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

    changeSet(author: "1200 (generated)", id: "1499752368467-14") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-15") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-16") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-17") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-18") {
        createTable(tableName: "USERLG") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "USERLGPK")
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

    changeSet(author: "1200 (generated)", id: "1499752368467-19") {
        createTable(tableName: "USERLG_ROLELG") {
            column(name: "userlg_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rolelg_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-20") {
        createTable(tableName: "categorias_materiales") {
            column(name: "id_material", type: "VARCHAR(255)")

            column(name: "material_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-21") {
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

    changeSet(author: "1200 (generated)", id: "1499752368467-22") {
        createTable(tableName: "evento_exclude_days") {
            column(name: "evento_id", type: "BIGINT")

            column(name: "exclude_days_date", type: "timestamp")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-23") {
        createTable(tableName: "evento_recur_days_of_week") {
            column(name: "evento_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "recur_days_of_week_integer", type: "INT")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-24") {
        createTable(tableName: "lineas_maquinas") {
            column(name: "id_linea", type: "VARCHAR(255)")

            column(name: "maquina_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-25") {
        createTable(tableName: "lista_verificacion_puntos") {
            column(name: "id_verficacion", type: "VARCHAR(255)")

            column(name: "punto_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-26") {
        createTable(tableName: "maquinas_subsistemas") {
            column(name: "id_maquina", type: "VARCHAR(255)")

            column(name: "subsistema_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-27") {
        createTable(tableName: "plantas_lineas") {
            column(name: "id_planta", type: "VARCHAR(255)")

            column(name: "linea_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-28") {
        createTable(tableName: "puntos_materiales") {
            column(name: "punto_materiales_id", type: "VARCHAR(255)")

            column(name: "material_id", type: "VARCHAR(255)")

            column(name: "ws_punto_materiales_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-29") {
        createTable(tableName: "rolelg_lineas") {
            column(name: "rolelg_lineas_id", type: "BIGINT")

            column(name: "linea_id", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-30") {
        addPrimaryKey(columnNames: "ID", constraintName: "CATEGORIASPK", tableName: "CATEGORIAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-31") {
        addPrimaryKey(columnNames: "ID", constraintName: "ESTANDARESPK", tableName: "ESTANDARES")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-32") {
        addPrimaryKey(columnNames: "ID", constraintName: "HORARIOSPK", tableName: "HORARIOS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-33") {
        addPrimaryKey(columnNames: "ID", constraintName: "LINEASPK", tableName: "LINEAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-34") {
        addPrimaryKey(columnNames: "ID", constraintName: "LISTA_VERIFICACIONPK", tableName: "LISTA_VERIFICACION")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-35") {
        addPrimaryKey(columnNames: "ID", constraintName: "MAQUINASPK", tableName: "MAQUINAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-36") {
        addPrimaryKey(columnNames: "ID", constraintName: "MATERIALESPK", tableName: "MATERIALES")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-37") {
        addPrimaryKey(columnNames: "ID", constraintName: "PIEZASPK", tableName: "PIEZAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-38") {
        addPrimaryKey(columnNames: "ID", constraintName: "PLANTASPK", tableName: "PLANTAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-39") {
        addPrimaryKey(columnNames: "ID", constraintName: "PUNTOSPK", tableName: "PUNTOS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-40") {
        addPrimaryKey(columnNames: "ID", constraintName: "PUNTOS_RESPUESTAPK", tableName: "PUNTOS_RESPUESTA")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-41") {
        addPrimaryKey(columnNames: "ID", constraintName: "SESIONPK", tableName: "SESION")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-42") {
        addPrimaryKey(columnNames: "ID", constraintName: "SUBSISTEMASPK", tableName: "SUBSISTEMAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-43") {
        addPrimaryKey(columnNames: "ID", constraintName: "TIPO_MANTENIMIENTOPK", tableName: "TIPO_MANTENIMIENTO")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-44") {
        addPrimaryKey(columnNames: "ID", constraintName: "TIPO_VERIFICACIONPK", tableName: "TIPO_VERIFICACION")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-45") {
        addPrimaryKey(columnNames: "userlg_id, rolelg_id", constraintName: "USERLG_ROLELGPK", tableName: "USERLG_ROLELG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-46") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLELGAUTHORITY_COL", tableName: "ROLELG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-47") {
        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERLGUSERNAME_COL", tableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-48") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "USERLG", constraintName: "FK_24k98xh51vbpg1yr9195wlfvo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-49") {
        addForeignKeyConstraint(baseColumnNames: "mantenimiento_id", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_2ixc6ba0wq0yeodnx3lmq0fnk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "TIPO_MANTENIMIENTO")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-50") {
        addForeignKeyConstraint(baseColumnNames: "id_verficacion", baseTableName: "lista_verificacion_puntos", constraintName: "FK_2qr4myo7es0ethvy2fe4ohfm5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LISTA_VERIFICACION")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-51") {
        addForeignKeyConstraint(baseColumnNames: "subsistema_id", baseTableName: "PIEZAS", constraintName: "FK_2xgmqbmukypnp1s3j8gy7v91y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SUBSISTEMAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-52") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "TIPO_VERIFICACION", constraintName: "FK_2yuqqk42ay8858w74quo23b3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-53") {
        addForeignKeyConstraint(baseColumnNames: "linea_id", baseTableName: "rolelg_lineas", constraintName: "FK_330jmeqtw5f4cl5uac4uf9eop", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LINEAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-54") {
        addForeignKeyConstraint(baseColumnNames: "maquina_id", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_35guspjpgy3i2ctpgkabed91f", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MAQUINAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-55") {
        addForeignKeyConstraint(baseColumnNames: "punto_id", baseTableName: "lista_verificacion_puntos", constraintName: "FK_3r5f7eif9hlx62a7ipvp3gqog", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PUNTOS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-56") {
        addForeignKeyConstraint(baseColumnNames: "id_maquina", baseTableName: "maquinas_subsistemas", constraintName: "FK_48canayvw1qddjaoojq4m8xoh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MAQUINAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-57") {
        addForeignKeyConstraint(baseColumnNames: "punto_id", baseTableName: "PUNTOS_RESPUESTA", constraintName: "FK_4ji4a0aaxeorlh229j65cnkpg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PUNTOS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-58") {
        addForeignKeyConstraint(baseColumnNames: "categoria_id", baseTableName: "MATERIALES", constraintName: "FK_4jjmcc53j5a0kdipioxsn9w1y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "CATEGORIAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-59") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "HORARIOS", constraintName: "FK_5a62gb4a1mdbsxdlb1yk3yp8m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-60") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "ROLELG", constraintName: "FK_7cqfvyadp3fwwcayglbipxk6i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-61") {
        addForeignKeyConstraint(baseColumnNames: "linea_id", baseTableName: "MAQUINAS", constraintName: "FK_8rwjueosk6frhs9u8anuhlcqf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LINEAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-62") {
        addForeignKeyConstraint(baseColumnNames: "punto_materiales_id", baseTableName: "puntos_materiales", constraintName: "FK_930skh71k3tqf9x51svk7hx79", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PUNTOS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-63") {
        addForeignKeyConstraint(baseColumnNames: "maquina_id", baseTableName: "lineas_maquinas", constraintName: "FK_95sdph69mf2kbfyg62n4rd0em", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MAQUINAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-64") {
        addForeignKeyConstraint(baseColumnNames: "tipo_id", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_9k4anxua924qfq26ft5u4s19r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "TIPO_VERIFICACION")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-65") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "MAQUINAS", constraintName: "FK_9vfncwxkyoq82svt8lrrrujb2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-66") {
        addForeignKeyConstraint(baseColumnNames: "id_linea", baseTableName: "lineas_maquinas", constraintName: "FK_aqbuiaoccfrjl5x5lx9p8k33m", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LINEAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-67") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "TIPO_MANTENIMIENTO", constraintName: "FK_day70p01cpsk26fv7od96p5hu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-68") {
        addForeignKeyConstraint(baseColumnNames: "evento_id", baseTableName: "evento_recur_days_of_week", constraintName: "FK_dptf0r647p1yysxgwc56plt4e", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "evento")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-69") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "PUNTOS", constraintName: "FK_e4iolq4lbpv4jwgb3447w8y39", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-70") {
        addForeignKeyConstraint(baseColumnNames: "verificacion_id", baseTableName: "PUNTOS", constraintName: "FK_eq4htewduf1xj89g0w58yoh54", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LISTA_VERIFICACION")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-71") {
        addForeignKeyConstraint(baseColumnNames: "rolelg_lineas_id", baseTableName: "rolelg_lineas", constraintName: "FK_fyb2wnsav7n41100961ffa8l6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ROLELG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-72") {
        addForeignKeyConstraint(baseColumnNames: "linea_id", baseTableName: "plantas_lineas", constraintName: "FK_g4lwyaffts5pfwus24wy66ywo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LINEAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-73") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "SUBSISTEMAS", constraintName: "FK_gbyvmfiqcag3rhdievf2myi66", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-74") {
        addForeignKeyConstraint(baseColumnNames: "material_id", baseTableName: "puntos_materiales", constraintName: "FK_gysruu8664aece5pjo5hj1g9a", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MATERIALES")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-75") {
        addForeignKeyConstraint(baseColumnNames: "id_material", baseTableName: "categorias_materiales", constraintName: "FK_hm1dn26ka4rxc75fyfh7t2go9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "CATEGORIAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-76") {
        addForeignKeyConstraint(baseColumnNames: "ws_punto_materiales_id", baseTableName: "puntos_materiales", constraintName: "FK_itnpmw65dbg1qhu1tvymh59xu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PUNTOS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-77") {
        addForeignKeyConstraint(baseColumnNames: "evento_id", baseTableName: "evento_exclude_days", constraintName: "FK_kafiqbg82bo58vxdklf2n54tp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "evento")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-78") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "ESTANDARES", constraintName: "FK_kumg8javulgicqi7mg0p7f4ev", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-79") {
        addForeignKeyConstraint(baseColumnNames: "subsistema_id", baseTableName: "PUNTOS", constraintName: "FK_kw5r78qnag76agodar4fjvlml", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SUBSISTEMAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-80") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "PLANTAS", constraintName: "FK_l0ba9vm8mf8ys6phca3o2a3fe", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-81") {
        addForeignKeyConstraint(baseColumnNames: "material_id", baseTableName: "categorias_materiales", constraintName: "FK_lisslw8n5vt21g7q6i0ll9qmv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MATERIALES")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-82") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "CATEGORIAS", constraintName: "FK_loptcxa0xiobxxuvfw5y9egwp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-83") {
        addForeignKeyConstraint(baseColumnNames: "planta_id", baseTableName: "LINEAS", constraintName: "FK_luj2hmq3oaies20twvllvh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PLANTAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-84") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "MATERIALES", constraintName: "FK_m0jf9bvjah3ge0rdq8iumsm3s", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-85") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "PIEZAS", constraintName: "FK_me7hdac0gs0101vawfeoyfgk5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-86") {
        addForeignKeyConstraint(baseColumnNames: "rolelg_id", baseTableName: "USERLG_ROLELG", constraintName: "FK_njvhvoi6k6hd61s7pl6oshjkk", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "ROLELG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-87") {
        addForeignKeyConstraint(baseColumnNames: "userlg_id", baseTableName: "USERLG_ROLELG", constraintName: "FK_odoqthc1493antalg1m5x9754", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-88") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "LISTA_VERIFICACION", constraintName: "FK_pwufvoak8n90c6netur2v2yy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-89") {
        addForeignKeyConstraint(baseColumnNames: "id_autor", baseTableName: "LINEAS", constraintName: "FK_qi9jc3yot00l5utenoi7hcdfv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "USERLG")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-90") {
        addForeignKeyConstraint(baseColumnNames: "subsistema_id", baseTableName: "maquinas_subsistemas", constraintName: "FK_qxdqyiugodcivrwb5n4u9kntq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "SUBSISTEMAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-91") {
        addForeignKeyConstraint(baseColumnNames: "verificacion_id", baseTableName: "ESTANDARES", constraintName: "FK_sj6ghps0bisamgqd8xrytdox9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "LISTA_VERIFICACION")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-92") {
        addForeignKeyConstraint(baseColumnNames: "id_planta", baseTableName: "plantas_lineas", constraintName: "FK_t22ptpt219brhw4d8yq1kkg56", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "PLANTAS")
    }

    changeSet(author: "1200 (generated)", id: "1499752368467-93") {
        addForeignKeyConstraint(baseColumnNames: "maquina_id", baseTableName: "SUBSISTEMAS", constraintName: "FK_t61sffoyp8bfi6gcmfsrurw8i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "ID", referencedTableName: "MAQUINAS")
    }
}
