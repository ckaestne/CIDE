package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class instancedecl extends definition {
  public instancedecl(context context3, inst inst, whereDecls whereDecls1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<context>("context3", context3),
      new PropertyOne<inst>("inst", inst),
      new PropertyZeroOrOne<whereDecls>("whereDecls1", whereDecls1)
    }, firstToken, lastToken);
  }
  public instancedecl(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new instancedecl(cloneProperties(),firstToken,lastToken);
  }
  public context getContext3() {
    return ((PropertyZeroOrOne<context>)getProperty("context3")).getValue();
  }
  public inst getInst() {
    return ((PropertyOne<inst>)getProperty("inst")).getValue();
  }
  public whereDecls getWhereDecls1() {
    return ((PropertyZeroOrOne<whereDecls>)getProperty("whereDecls1")).getValue();
  }
}
