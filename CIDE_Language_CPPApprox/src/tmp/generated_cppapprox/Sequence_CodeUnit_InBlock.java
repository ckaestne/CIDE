package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Sequence_CodeUnit_InBlock extends GenASTNode {
  public Sequence_CodeUnit_InBlock(ArrayList<CodeUnit_InBlock> codeUnit_InBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<CodeUnit_InBlock>("codeUnit_InBlock", codeUnit_InBlock)
    }, firstToken, lastToken);
  }
  public Sequence_CodeUnit_InBlock(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Sequence_CodeUnit_InBlock(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<CodeUnit_InBlock> getCodeUnit_InBlock() {
    return ((PropertyZeroOrMore<CodeUnit_InBlock>)getProperty("codeUnit_InBlock")).getValue();
  }
}
