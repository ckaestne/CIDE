package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class CodeUnit_InBlock10 extends CodeUnit_InBlock {
  public CodeUnit_InBlock10(GotoLabel gotoLabel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<GotoLabel>("gotoLabel", gotoLabel)
    }, firstToken, lastToken);
  }
  public CodeUnit_InBlock10(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new CodeUnit_InBlock10(cloneProperties(),firstToken,lastToken);
  }
  public GotoLabel getGotoLabel() {
    return ((PropertyOne<GotoLabel>)getProperty("gotoLabel")).getValue();
  }
}
