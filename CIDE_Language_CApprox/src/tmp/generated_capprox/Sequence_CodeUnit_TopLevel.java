package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Sequence_CodeUnit_TopLevel extends GenASTNode {
  public Sequence_CodeUnit_TopLevel(ArrayList<CodeUnit_TopLevel> codeUnit_TopLevel, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<CodeUnit_TopLevel>("codeUnit_TopLevel", codeUnit_TopLevel)
    }, firstToken, lastToken);
  }
  public Sequence_CodeUnit_TopLevel(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Sequence_CodeUnit_TopLevel(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<CodeUnit_TopLevel> getCodeUnit_TopLevel() {
    return ((PropertyZeroOrMore<CodeUnit_TopLevel>)getProperty("codeUnit_TopLevel")).getValue();
  }
}
