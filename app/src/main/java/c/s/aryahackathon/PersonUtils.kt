package c.s.aryahackathon

class PersonUtils {

    var personFirstName:String?=null
    var personLastName:String?=null
    var jobProfile:String?=null

    fun getFirstName():String{
        return personFirstName!!
    }

    fun getLastName():String{
        return personLastName!!
    }

    fun getProfile():String{
        return jobProfile!!
    }

    fun setname(name:String){
        this.personFirstName=name
    }

    fun setlasname(lastName:String){
        this.personLastName=lastName
    }
    fun setjobprofile(jobprofile:String){
        this.jobProfile=jobprofile
    }


}
