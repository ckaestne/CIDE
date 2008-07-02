package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class fielddeclaration extends GenASTNode {
  public fielddeclaration(varList varList, functiontype functiontype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<varList>("varList", varList),
      new PropertyOne<functiontype>("functiontype", functiontype)
    }, firstToken, lastToken);
  }
  public fielddeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new fielddeclaration(cloneProperties(),firstToken,lastToken);
  }
  public varList getVarList() {
    return ((PropertyOne<varList>)getProperty("varList")).getValue();
  }
  public functiontype getFunctiontype() {
    return ((PropertyOne<functiontype>)getProperty("functiontype")).getValue();
  }
}
