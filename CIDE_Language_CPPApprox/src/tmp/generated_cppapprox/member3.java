package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class member3 extends member {
  public member3(CodeUnit_TopLevel codeUnit_TopLevel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CodeUnit_TopLevel>("codeUnit_TopLevel", codeUnit_TopLevel)
    }, firstToken, lastToken);
  }
  public member3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new member3(cloneProperties(),firstToken,lastToken);
  }
  public CodeUnit_TopLevel getCodeUnit_TopLevel() {
    return ((PropertyOne<CodeUnit_TopLevel>)getProperty("codeUnit_TopLevel")).getValue();
  }
}
