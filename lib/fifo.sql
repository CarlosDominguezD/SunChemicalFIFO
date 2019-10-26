-- MySQL dump 10.13  Distrib 5.5.11, for Win32 (x86)
--
-- Host: localhost    Database: fifo
-- ------------------------------------------------------
-- Server version	5.5.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fbl3m`
--

DROP TABLE IF EXISTS `fbl3m`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fbl3m` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Index` varchar(250) DEFAULT NULL,
  `Document_Number` varchar(250) DEFAULT NULL,
  `Document_type` varchar(250) DEFAULT NULL,
  `Document_Date` varchar(250) DEFAULT NULL,
  `Posting_Date` varchar(250) DEFAULT NULL,
  `Cost_Center` varchar(250) DEFAULT NULL,
  `Profit_Center` varchar(250) DEFAULT NULL,
  `Year_month` varchar(250) DEFAULT NULL,
  `Account` varchar(250) DEFAULT NULL,
  `Plant` varchar(250) DEFAULT NULL,
  `Material` varchar(250) DEFAULT NULL,
  `Quantity` varchar(250) DEFAULT NULL,
  `Amount_in_local_currency` varchar(250) DEFAULT NULL,
  `Local_Currency` varchar(250) DEFAULT NULL,
  `Purchasing_Document` varchar(250) DEFAULT NULL,
  `Reference` varchar(250) DEFAULT NULL,
  `Document_currency` varchar(250) DEFAULT NULL,
  `Offsetting_acct_no` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure` varchar(250) DEFAULT NULL,
  `Alternative_Account_No` varchar(250) DEFAULT NULL,
  `Transaction_Code` varchar(250) DEFAULT NULL,
  `Text` varchar(250) DEFAULT NULL,
  `Assignment` varchar(250) DEFAULT NULL,
  `Clasificación` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fbl3m`
--

LOCK TABLES `fbl3m` WRITE;
/*!40000 ALTER TABLE `fbl3m` DISABLE KEYS */;
/*!40000 ALTER TABLE `fbl3m` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mb51`
--

DROP TABLE IF EXISTS `mb51`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mb51` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Plant` varchar(250) DEFAULT NULL,
  `Purchase_order` varchar(250) DEFAULT NULL,
  `Material` varchar(250) DEFAULT NULL,
  `Material_Description` varchar(250) DEFAULT NULL,
  `Batch` varchar(250) DEFAULT NULL,
  `Movement_type` varchar(250) DEFAULT NULL,
  `Movement_Type_Text` varchar(250) DEFAULT NULL,
  `Item` varchar(250) DEFAULT NULL,
  `Quantity` varchar(250) DEFAULT NULL,
  `Qty_in:unit_of_entry` varchar(250) DEFAULT NULL,
  `Unit_of_Entry` varchar(250) DEFAULT NULL,
  `Amt_in_loc_cur` varchar(250) DEFAULT NULL,
  `Currency` varchar(250) DEFAULT NULL,
  `Storage_Location` varchar(250) DEFAULT NULL,
  `Posting_Date` varchar(250) DEFAULT NULL,
  `Document_Date` varchar(250) DEFAULT NULL,
  `Material_Document` varchar(250) DEFAULT NULL,
  `User_Name` varchar(250) DEFAULT NULL,
  `Vendor` varchar(250) DEFAULT NULL,
  `Order` varchar(250) DEFAULT NULL,
  `Vendor_Name` varchar(250) DEFAULT NULL,
  `Vendor_Type` varchar(250) DEFAULT NULL,
  `Month` varchar(250) DEFAULT NULL,
  `Period` varchar(250) DEFAULT NULL,
  `Material_Type` varchar(250) DEFAULT NULL,
  `Profit_Center` varchar(250) DEFAULT NULL,
  `link1_PO_+_Material` varchar(250) DEFAULT NULL,
  `link2_PO_+_position` varchar(250) DEFAULT NULL,
  `Referencia_&_vendor` varchar(250) DEFAULT NULL,
  `TotalQ_ME80FN` varchar(250) DEFAULT NULL,
  `TotalQ_%` varchar(250) DEFAULT NULL,
  `TOTAL_INVOICE_VALUE` varchar(250) DEFAULT NULL,
  `Factura_Value_Unit` varchar(250) DEFAULT NULL,
  `PIR_%` varchar(250) DEFAULT NULL,
  `Moneda` varchar(250) DEFAULT NULL,
  `Freight` varchar(250) DEFAULT NULL,
  `Dutys` varchar(250) DEFAULT NULL,
  `Arancel` varchar(250) DEFAULT NULL,
  `Ajuste_PIR` varchar(250) DEFAULT NULL,
  `Otros` varchar(250) DEFAULT NULL,
  `Total_Costos_Adicionales` varchar(250) DEFAULT NULL,
  `Participac_Adicionales` varchar(250) DEFAULT NULL,
  `Total_Costos` varchar(250) DEFAULT NULL,
  `Unitario_final_FIFO` varchar(250) DEFAULT NULL,
  `Unitario_estandar` varchar(250) DEFAULT NULL,
  `%_Real_Vs_Estándar` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mb51`
--

LOCK TABLES `mb51` WRITE;
/*!40000 ALTER TABLE `mb51` DISABLE KEYS */;
/*!40000 ALTER TABLE `mb51` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `me80fn`
--

DROP TABLE IF EXISTS `me80fn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `me80fn` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Index` varchar(250) DEFAULT NULL,
  `Index2` varchar(250) DEFAULT NULL,
  `Purchasing_Document` varchar(250) DEFAULT NULL,
  `Material_Doc_Year` varchar(250) DEFAULT NULL,
  `Material_Document` varchar(250) DEFAULT NULL,
  `Document_Date` varchar(250) DEFAULT NULL,
  `Material` varchar(250) DEFAULT NULL,
  `Short_Text` varchar(250) DEFAULT NULL,
  `Batch` varchar(250) DEFAULT NULL,
  `Item` varchar(250) DEFAULT NULL,
  `Movement_type` varchar(250) DEFAULT NULL,
  `Posting_Date` varchar(250) DEFAULT NULL,
  `Delivery_Completed` varchar(250) DEFAULT NULL,
  `Plant` varchar(250) DEFAULT NULL,
  `Quantity` varchar(250) DEFAULT NULL,
  `Amt_in_loc_cur` varchar(250) DEFAULT NULL,
  `Amount` varchar(250) DEFAULT NULL,
  `Currency` varchar(250) DEFAULT NULL,
  `Valuation_Type` varchar(250) DEFAULT NULL,
  `Entry_Date` varchar(250) DEFAULT NULL,
  `Local_currency` varchar(250) DEFAULT NULL,
  `Reference_Doc_Item` varchar(250) DEFAULT NULL,
  `Invoice_Value` varchar(250) DEFAULT NULL,
  `Invoice_Value_in_FC` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `me80fn`
--

LOCK TABLES `me80fn` WRITE;
/*!40000 ALTER TABLE `me80fn` DISABLE KEYS */;
/*!40000 ALTER TABLE `me80fn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mrpdata`
--

DROP TABLE IF EXISTS `mrpdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mrpdata` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Material` varchar(250) DEFAULT NULL,
  `Material_Description` varchar(250) DEFAULT NULL,
  `Material_Group` varchar(250) DEFAULT NULL,
  `Profit_Center` varchar(250) DEFAULT NULL,
  `Product_Hierarchy` varchar(250) DEFAULT NULL,
  `Product_Hierarchy_Description` varchar(250) DEFAULT NULL,
  `Material_Type` varchar(250) DEFAULT NULL,
  `Material_type_descr` varchar(250) DEFAULT NULL,
  `Plant` varchar(250) DEFAULT NULL,
  `Plant_Name` varchar(250) DEFAULT NULL,
  `Ext_Material_Group` varchar(250) DEFAULT NULL,
  `Matl_Grp_Pack_Matls` varchar(250) DEFAULT NULL,
  `MGPM_Description` varchar(250) DEFAULT NULL,
  `MRP_profile` varchar(250) DEFAULT NULL,
  `MRP_Profile_Description` varchar(250) DEFAULT NULL,
  `Lot_size` varchar(250) DEFAULT NULL,
  `Procurement_type` varchar(250) DEFAULT NULL,
  `MRP_Controller` varchar(250) DEFAULT NULL,
  `MRP_Type` varchar(250) DEFAULT NULL,
  `Purchasing_Group` varchar(250) DEFAULT NULL,
  `Plant_sp_matl_status` varchar(250) DEFAULT NULL,
  `Mat_Status_Description` varchar(250) DEFAULT NULL,
  `Do_Not_Cost` varchar(250) DEFAULT NULL,
  `Storage_loc_for_EP` varchar(250) DEFAULT NULL,
  `Prod_stor_location` varchar(250) DEFAULT NULL,
  `Production_unit` varchar(250) DEFAULT NULL,
  `Created_On` varchar(250) DEFAULT NULL,
  `Availability_check` varchar(250) DEFAULT NULL,
  `Loading_Group` varchar(250) DEFAULT NULL,
  `Tot_repl_lead_time` varchar(250) DEFAULT NULL,
  `Underdely_tolerance` varchar(250) DEFAULT NULL,
  `Unltd_Overdelivery` varchar(250) DEFAULT NULL,
  `Overdely_tolerance` varchar(250) DEFAULT NULL,
  `In_house_production` varchar(250) DEFAULT NULL,
  `Prodn_Supervisor` varchar(250) DEFAULT NULL,
  `Backflush` varchar(250) DEFAULT NULL,
  `Individual_coll` varchar(250) DEFAULT NULL,
  `Rounding_value` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure_1` varchar(250) DEFAULT NULL,
  `Reorder_Point` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure_2` varchar(250) DEFAULT NULL,
  `Assembly_scrap_%` varchar(250) DEFAULT NULL,
  `GR_processing_time` varchar(250) DEFAULT NULL,
  `Unit_of_issue` varchar(250) DEFAULT NULL,
  `Valid_from` varchar(250) DEFAULT NULL,
  `Batch_management_1` varchar(250) DEFAULT NULL,
  `Valuation_Category` varchar(250) DEFAULT NULL,
  `DF_at_plant_level` varchar(250) DEFAULT NULL,
  `DF_at_client_level` varchar(250) DEFAULT NULL,
  `Old_material_number` varchar(250) DEFAULT NULL,
  `Order_Unit` varchar(250) DEFAULT NULL,
  `Lab_Office` varchar(250) DEFAULT NULL,
  `Lab_description` varchar(250) DEFAULT NULL,
  `Gross_Weight` varchar(250) DEFAULT NULL,
  `Weight_Unit_1` varchar(250) DEFAULT NULL,
  `Net_Weight` varchar(250) DEFAULT NULL,
  `Weight_Unit_2` varchar(250) DEFAULT NULL,
  `Container_reqmts` varchar(250) DEFAULT NULL,
  `Storage_conditions` varchar(250) DEFAULT NULL,
  `Temp_conditions` varchar(250) DEFAULT NULL,
  `Transportation_Group` varchar(250) DEFAULT NULL,
  `Batch_management_2` varchar(250) DEFAULT NULL,
  `Packaging_mat_type` varchar(250) DEFAULT NULL,
  `X_plant_matl_status` varchar(250) DEFAULT NULL,
  `Min_Rem_Shelf_Life` varchar(250) DEFAULT NULL,
  `Total_shelf_life` varchar(250) DEFAULT NULL,
  `Language_Key` varchar(250) DEFAULT NULL,
  `Created_By` varchar(250) DEFAULT NULL,
  `Safety_stock` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure_3` varchar(250) DEFAULT NULL,
  `Fixed_lot_size` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure_4` varchar(250) DEFAULT NULL,
  `Maximum_Lot_Size_1` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure_5` varchar(250) DEFAULT NULL,
  `Minimum_Lot_Size_2` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure_6` varchar(250) DEFAULT NULL,
  `Costing_Lot_Size` varchar(250) DEFAULT NULL,
  `Base_Unit_of_Measure_7` varchar(250) DEFAULT NULL,
  `Prod_Sched_Profile` varchar(250) DEFAULT NULL,
  `Prod_Sched_Profile_Desc` varchar(250) DEFAULT NULL,
  `Planned_Deliv_Time` varchar(250) DEFAULT NULL,
  `Special_procurement` varchar(250) DEFAULT NULL,
  `SP_Key_Description` varchar(250) DEFAULT NULL,
  `ABC_Indicator` varchar(250) DEFAULT NULL,
  `STime_period_profile` varchar(250) DEFAULT NULL,
  `Comm_imp_code_no` varchar(250) DEFAULT NULL,
  `Country_of_origin` varchar(20) DEFAULT NULL,
  `SpecProcurem_Costing` varchar(20) DEFAULT NULL,
  `Overhead_Group` varchar(20) DEFAULT NULL,
  `TDS` varchar(20) DEFAULT NULL,
  `Period_Indicator` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mrpdata`
--

LOCK TABLES `mrpdata` WRITE;
/*!40000 ALTER TABLE `mrpdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `mrpdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Vendors` varchar(250) DEFAULT NULL,
  `Name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-26 10:34:33
