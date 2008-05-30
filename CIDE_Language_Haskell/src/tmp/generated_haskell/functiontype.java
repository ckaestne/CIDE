package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class functiontype extends GenASTNode {
  public functiontype(type type, ArrayList<typArr> typArr, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<type>("type", type),
      new PropertyZeroOrMore<typArr>("typArr", typArr)
    }, firstToken, lastToken);
  }
  public functiontype(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new functiontype(cloneProperties(),firstToken,lastToken);
  }
  public type getType() {
    return ((PropertyOne<type>)getProperty("type")).getValue();
  }
  public ArrayList<typArr> getTypArr() {
    return ((PropertyZeroOrMore<typArr>)getProperty("typArr")).getValue();
  }
}
