package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class dotted_name extends GenASTNode {
  public dotted_name(AnyName anyName, ArrayList<AnyName> anyName1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<AnyName>("anyName", anyName),
      new PropertyZeroOrMore<AnyName>("anyName1", anyName1)
    }, firstToken, lastToken);
  }
  public dotted_name(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new dotted_name(cloneProperties(),firstToken,lastToken);
  }
  public AnyName getAnyName() {
    return ((PropertyOne<AnyName>)getProperty("anyName")).getValue();
  }
  public ArrayList<AnyName> getAnyName1() {
    return ((PropertyZeroOrMore<AnyName>)getProperty("anyName1")).getValue();
  }
}
