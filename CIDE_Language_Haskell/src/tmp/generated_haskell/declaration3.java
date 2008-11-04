package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class declaration3 extends declaration {
  public declaration3(varList varList, context context, functiontype functiontype, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<varList>("varList", varList),
      new PropertyZeroOrOne<context>("context", context),
      new PropertyOne<functiontype>("functiontype", functiontype)
    }, firstToken, lastToken);
  }
  public declaration3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new declaration3(cloneProperties(),firstToken,lastToken);
  }
  public varList getVarList() {
    return ((PropertyOne<varList>)getProperty("varList")).getValue();
  }
  public context getContext() {
    return ((PropertyZeroOrOne<context>)getProperty("context")).getValue();
  }
  public functiontype getFunctiontype() {
    return ((PropertyOne<functiontype>)getProperty("functiontype")).getValue();
  }
}
