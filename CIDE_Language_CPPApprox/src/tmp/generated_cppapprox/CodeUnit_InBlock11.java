package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CodeUnit_InBlock11 extends CodeUnit_InBlock {
  public CodeUnit_InBlock11(GotoLabel gotoLabel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<GotoLabel>("gotoLabel", gotoLabel)
    }, firstToken, lastToken);
  }
  public CodeUnit_InBlock11(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CodeUnit_InBlock11(cloneProperties(),firstToken,lastToken);
  }
  public GotoLabel getGotoLabel() {
    return ((PropertyOne<GotoLabel>)getProperty("gotoLabel")).getValue();
  }
}
