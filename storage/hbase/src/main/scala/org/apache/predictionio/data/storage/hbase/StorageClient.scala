/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.apache.predictionio.data.storage.hbase

import org.apache.predictionio.data.storage.BaseStorageClient
import org.apache.predictionio.data.storage.StorageClientConfig

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.client.Connection
import org.apache.hadoop.hbase.client.Admin

import grizzled.slf4j.Logging

case class HBClient(
  val conf: Configuration,
  val connection: Connection,
  val admin: Admin
)

class StorageClient(val config: StorageClientConfig)
  extends BaseStorageClient with Logging {

  val conf = HBaseConfiguration.create()
  val connection = ConnectionFactory.createConnection(conf)

  val client = HBClient(
    conf = conf,
    connection = connection,
    admin = connection.getAdmin()
  )

  override
  val prefix = "HB"
}
