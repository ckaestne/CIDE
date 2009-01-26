package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class test2 extends test {
  public test2(and_test and_test, ArrayList<and_test> and_test1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<and_test>("and_test", and_test),
      new PropertyZeroOrMore<and_test>("and_test1", and_test1)
    }, firstToken, lastToken);
  }
  public test2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new test2(cloneProperties(),firstToken,lastToken);
  }
  public and_test getAnd_test() {
    return ((PropertyOne<and_test>)getProperty("and_test")).getValue();
  }
  public ArrayList<and_test> getAnd_test1() {
    return ((PropertyZeroOrMore<and_test>)getProperty("and_test1")).getValue();
  }
}
