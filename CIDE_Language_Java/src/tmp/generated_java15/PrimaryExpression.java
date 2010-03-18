package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimaryExpression extends GenASTNode {
  public PrimaryExpression(PrimaryPrefix primaryPrefix, ArrayList<PrimarySuffix> primarySuffix, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<PrimaryPrefix>("primaryPrefix", primaryPrefix),
      new PropertyZeroOrMore<PrimarySuffix>("primarySuffix", primarySuffix)
    }, firstToken, lastToken);
  }
  public PrimaryExpression(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimaryExpression(cloneProperties(),firstToken,lastToken);
  }
  public PrimaryPrefix getPrimaryPrefix() {
    return ((PropertyOne<PrimaryPrefix>)getProperty("primaryPrefix")).getValue();
  }
  public ArrayList<PrimarySuffix> getPrimarySuffix() {
    return ((PropertyZeroOrMore<PrimarySuffix>)getProperty("primarySuffix")).getValue();
  }
}
