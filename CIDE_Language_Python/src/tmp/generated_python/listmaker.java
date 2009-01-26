package tmp.generated_python;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class listmaker extends GenASTNode {
  public listmaker(test test, listmakerEnd listmakerEnd, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<test>("test", test),
      new PropertyOne<listmakerEnd>("listmakerEnd", listmakerEnd)
    }, firstToken, lastToken);
  }
  public listmaker(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new listmaker(cloneProperties(),firstToken,lastToken);
  }
  public test getTest() {
    return ((PropertyOne<test>)getProperty("test")).getValue();
  }
  public listmakerEnd getListmakerEnd() {
    return ((PropertyOne<listmakerEnd>)getProperty("listmakerEnd")).getValue();
  }
}
