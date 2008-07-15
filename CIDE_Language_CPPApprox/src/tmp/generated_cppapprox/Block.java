package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Block extends GenASTNode {
  public Block(Sequence_CodeUnit_InBlock sequence_CodeUnit_InBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Sequence_CodeUnit_InBlock>("sequence_CodeUnit_InBlock", sequence_CodeUnit_InBlock)
    }, firstToken, lastToken);
  }
  public Block(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Block(cloneProperties(),firstToken,lastToken);
  }
  public Sequence_CodeUnit_InBlock getSequence_CodeUnit_InBlock() {
    return ((PropertyOne<Sequence_CodeUnit_InBlock>)getProperty("sequence_CodeUnit_InBlock")).getValue();
  }
}
