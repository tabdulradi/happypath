# happypath

Makes union types work with for-comprehensions (like scala.Either, but without the allocation cost).

## Usage

```scala
import com.abdulradi.happypath.ForSyntax

enum AppError derives ForSyntax:
  case Err1(foo: String)
  case Err2(bar: Int)

@main def app(): Unit = 
  val intOrErr: AppError | Int = 42
  val strOrErr: AppError | String = "test"

  for 
    i <- intOrErr 
    s <- strOrErr
  do println(s"$s $i")
```
