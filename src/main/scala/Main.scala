/**
  * Created by jim on 1/23/16.
  */

import com.google.common.base.Stopwatch
import com.typesafe.scalalogging.slf4j.Logger
import scala.collection.immutable.TreeMap
import java.util.concurrent.TimeUnit

object Main extends App {
  // var rbtree = new RedBlackTree4150(logger, stopWatch)
  var rbtree = new RedBlackTree4150()
}

// class RedBlackTree4150 (var loggerInst: Logger, var stopWatchInst: Stopwatch) {
class RedBlackTree4150() {
  var loggerInst = new Logger()
  var stopWatchInst = new Stopwatch()

  val powersList = List(10 to 20)

  // create a map to keep track of power of two and resulting timings
  var timingsMap: Map[Integer, Double] = Map()

  loggerInst.info("Starting program run...")

  loggerInst.info("To avoid cold start, creating two trees with 2^10 and 2^11 entries...")

  val singleRunTime = runTest(10 to 11)

  loggerInst.info("That warm up of runTest with 2^10 and 2^11 took " + singleRunTime + " ms")
  loggerInst.info("Now for the real test!\n\n")

  // call the runTest function once for each power of two we want, from powersList,
  // assign timingsMap the power of 2 value and results of runTest for that tree size
  timingsMap = for (i <- powersList; j <- runTest(i)) yield i -> j

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
    for (x <- powerOfTwo) {
      // set next entry in map to key, random number
      tree += (x -> math.random)
    }

    stopWatchInst.start()

    // now go through and look up all the values in the tree,
    for (x <- powerOfTwo) {
      // get the value, don't take the time/overhead to store it in a var, as we don't need it
      tree.get(x)
    }

    // stop watch check time report and return time
    stopWatchInst.stop()
    val totalTime = stopWatchInst.elapsed(TimeUnit.MILLISECONDS)
    loggerInst.info("run for 2 to the power of " + powerOfTwo + " took " + totalTime + " ms")
    return totalTime
  }


}
