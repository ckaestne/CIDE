package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Func extends CodeUnit_TopLevel {
  public Func(Function function, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Function>("function", function)
    }, firstToken, lastToken);
  }
  public Func(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Func(cloneProperties(),firstToken,lastToken);
  }
  public Function getFunction() {
    return ((PropertyOne<Function>)getProperty("function")).getValue();
  }
}
