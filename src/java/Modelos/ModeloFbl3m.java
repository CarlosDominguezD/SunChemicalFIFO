/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloFbl3m
{

    Integer Id;
    String Document_Number;
    String Document_type;
    String Document_Date;
    String Posting_Date;
    String Cost_Center;
    String Profit_Center;
    String YearMonth;
    String Account;
    String Plant;
    String Material;
    String Quantity;
    String Amount_in_local_currency;
    String Local_Currency;
    String Purchasing_Document;
    String Reference;
    String Document_currency;
    String Offsetting_acct_no;
    String Base_Unit_of_Measure;
    String Alternative_Account_No;
    String Transaction_Code;
    String Text;
    String Assignment;
    String Clasificacion;

    public ModeloFbl3m()
    {
    }

    public ModeloFbl3m(Integer Id, String Document_Number, String Document_type, String Document_Date, String Posting_Date, String Cost_Center, String Profit_Center, String YearMonth, String Account, String Plant, String Material, String Quantity, String Amount_in_local_currency, String Local_Currency, String Purchasing_Document, String Reference, String Document_currency, String Offsetting_acct_no, String Base_Unit_of_Measure, String Alternative_Account_No, String Transaction_Code, String Text, String Assignment, String Clasificacion)
    {
        this.Id = Id;
        this.Document_Number = Document_Number;
        this.Document_type = Document_type;
        this.Document_Date = Document_Date;
        this.Posting_Date = Posting_Date;
        this.Cost_Center = Cost_Center;
        this.Profit_Center = Profit_Center;
        this.YearMonth = YearMonth;
        this.Account = Account;
        this.Plant = Plant;
        this.Material = Material;
        this.Quantity = Quantity;
        this.Amount_in_local_currency = Amount_in_local_currency;
        this.Local_Currency = Local_Currency;
        this.Purchasing_Document = Purchasing_Document;
        this.Reference = Reference;
        this.Document_currency = Document_currency;
        this.Offsetting_acct_no = Offsetting_acct_no;
        this.Base_Unit_of_Measure = Base_Unit_of_Measure;
        this.Alternative_Account_No = Alternative_Account_No;
        this.Transaction_Code = Transaction_Code;
        this.Text = Text;
        this.Assignment = Assignment;
        this.Clasificacion = Clasificacion;
    }

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer Id)
    {
        this.Id = Id;
    }
   

    public String getDocument_Number()
    {
        return Document_Number;
    }

    public void setDocument_Number(String Document_Number)
    {
        this.Document_Number = Document_Number;
    }

    public String getDocument_type()
    {
        return Document_type;
    }

    public void setDocument_type(String Document_type)
    {
        this.Document_type = Document_type;
    }

    public String getDocument_Date()
    {
        return Document_Date;
    }

    public void setDocument_Date(String Document_Date)
    {
        this.Document_Date = Document_Date;
    }

    public String getPosting_Date()
    {
        return Posting_Date;
    }

    public void setPosting_Date(String Posting_Date)
    {
        this.Posting_Date = Posting_Date;
    }

    public String getCost_Center()
    {
        return Cost_Center;
    }

    public void setCost_Center(String Cost_Center)
    {
        this.Cost_Center = Cost_Center;
    }

    public String getProfit_Center()
    {
        return Profit_Center;
    }

    public void setProfit_Center(String Profit_Center)
    {
        this.Profit_Center = Profit_Center;
    }

    public String getYearMonth()
    {
        return YearMonth;
    }

    public void setYearMonth(String YearMonth)
    {
        this.YearMonth = YearMonth;
    }

    public String getAccount()
    {
        return Account;
    }

    public void setAccount(String Account)
    {
        this.Account = Account;
    }

    public String getPlant()
    {
        return Plant;
    }

    public void setPlant(String Plant)
    {
        this.Plant = Plant;
    }

    public String getMaterial()
    {
        return Material;
    }

    public void setMaterial(String Material)
    {
        this.Material = Material;
    }

    public String getQuantity()
    {
        return Quantity;
    }

    public void setQuantity(String Quantity)
    {
        this.Quantity = Quantity;
    }

    public String getAmount_in_local_currency()
    {
        return Amount_in_local_currency;
    }

    public void setAmount_in_local_currency(String Amount_in_local_currency)
    {
        this.Amount_in_local_currency = Amount_in_local_currency;
    }

    public String getLocal_Currency()
    {
        return Local_Currency;
    }

    public void setLocal_Currency(String Local_Currency)
    {
        this.Local_Currency = Local_Currency;
    }

    public String getPurchasing_Document()
    {
        return Purchasing_Document;
    }

    public void setPurchasing_Document(String Purchasing_Document)
    {
        this.Purchasing_Document = Purchasing_Document;
    }

    public String getReference()
    {
        return Reference;
    }

    public void setReference(String Reference)
    {
        this.Reference = Reference;
    }

    public String getDocument_currency()
    {
        return Document_currency;
    }

    public void setDocument_currency(String Document_currency)
    {
        this.Document_currency = Document_currency;
    }

    public String getOffsetting_acct_no()
    {
        return Offsetting_acct_no;
    }

    public void setOffsetting_acct_no(String Offsetting_acct_no)
    {
        this.Offsetting_acct_no = Offsetting_acct_no;
    }

    public String getBase_Unit_of_Measure()
    {
        return Base_Unit_of_Measure;
    }

    public void setBase_Unit_of_Measure(String Base_Unit_of_Measure)
    {
        this.Base_Unit_of_Measure = Base_Unit_of_Measure;
    }

    public String getAlternative_Account_No()
    {
        return Alternative_Account_No;
    }

    public void setAlternative_Account_No(String Alternative_Account_No)
    {
        this.Alternative_Account_No = Alternative_Account_No;
    }

    public String getTransaction_Code()
    {
        return Transaction_Code;
    }

    public void setTransaction_Code(String Transaction_Code)
    {
        this.Transaction_Code = Transaction_Code;
    }

    public String getText()
    {
        return Text;
    }

    public void setText(String Text)
    {
        this.Text = Text;
    }

    public String getAssignment()
    {
        return Assignment;
    }

    public void setAssignment(String Assignment)
    {
        this.Assignment = Assignment;
    }

    public String getClasificacion()
    {
        return Clasificacion;
    }

    public void setClasificacion(String Clasificacion)
    {
        this.Clasificacion = Clasificacion;
    }

}
