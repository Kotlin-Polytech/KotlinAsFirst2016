package lesson1.task1.lesson1

/**
 * Created by User on 2016/9/14.
 */
fun quadraticRootProduct(a:Double, b:Double, c:Double):Double{
    val x1=(-b+sd)/(2*a)
    val x2=(-b-sd)/(2*a)
    val sd=sqr(discriminnant(a,b,c))
    return x1*x2
}