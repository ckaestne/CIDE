package tmp.generated_gcide;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Mult1 extends Mult {
  public Mult1(OneOrMore oneOrMore, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<OneOrMore>("oneOrMore", oneOrMore)
    }, firstToken, lastToken);
  }
  public Mult1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Mult1(cloneProperties(),firstToken,lastToken);
  }
  public OneOrMore getOneOrMore() {
    return ((PropertyOne<OneOrMore>)getProperty("oneOrMore")).getValue();
  }
}
