package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class tryEnd1 extends tryEnd {
  public tryEnd1(ArrayList<except_clause> except_clause, suite suite, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<except_clause>("except_clause", except_clause),
      new PropertyZeroOrOne<suite>("suite", suite)
    }, firstToken, lastToken);
  }
  public tryEnd1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new tryEnd1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<except_clause> getExcept_clause() {
    return ((PropertyOneOrMore<except_clause>)getProperty("except_clause")).getValue();
  }
  public suite getSuite() {
    return ((PropertyZeroOrOne<suite>)getProperty("suite")).getValue();
  }
}
