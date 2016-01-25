/**
  * Created by jim on 1/23/16.
  */

import com.google.common.base.Stopwatch
import com.typesafe.scalalogging.slf4j._
import scala.collection.immutable.TreeMap
import java.util.concurrent.TimeUnit

object RedBlackTree4150 extends App {
  // val rbtree = new RedBlackTree4150()
// }

// class RedBlackTree4150 {

  // create an array of the powers of 2 we want to test
  //val powersList = List(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
  val powersList = List(10 to 20)

  // create a map to keep track of power of two and resulting timings
  var timingsMap:Map[Integer, Double] = Map()

  // setup logging
  var logger = new Logger

  logger.info("Starting program run...")

  logger.info("To avoid cold start, creating two trees with 2^10 and 2^11 entries...")

  val singleRunTime = runTest(10 to 11)

  logger.info("That warm up of runTest with 2^10 and 2^11 took " + singleRunTime + " ms")
  logger.info("Now for the real test!\n\n")

  // call the runTest function once for each power of two we want, from powersList,
  // assign timingsMap the power of 2 value and results of runTest for that tree size
  timingsMap = for ( i <- powersList; j <- runTest(i)) yield  i -> j

  // now iterate through timingsMap to create a table of values
  println("power of two | timing")
  println("---------------------")
  timingsMap foreach ((timingStat) => println(timingStat._1 + "           | " + timingStat._2))

  // function takes an integer param, builts a tree and populates it with that size
  // then reads each element
  def runTest(powerOfTwo: Range): Double = {

    // create the tree here
    var tree = new TreeMap[Int, Double]

    // we only care to create a tree with integer keys, not what the value is
    for (x <- powerOfTwo){
      // set next entry in map to key, random number
      tree += (x -> math.random)
    }

    // setup timing
    val stopWatch = new Stopwatch()
    stopWatch.start()

    // now go through and look up all the values in the tree,
    for (x <- powerOfTwo){
      // get the value, don't take the time/overhead to store it in a var, as we don't need it
      tree.get(x)
    }

    stopWatch.stop()

    val totalTime = stopWatch.elapsed(TimeUnit.MILLISECONDS)

    logger.info("run for 2 to the power of " + powerOfTwo + " took " + totalTime + " ms")



    return 0.0
  }




}
